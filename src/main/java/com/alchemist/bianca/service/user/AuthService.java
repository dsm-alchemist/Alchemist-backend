package com.alchemist.bianca.service.user;

import com.alchemist.bianca.dto.user.request.*;
import com.alchemist.bianca.dto.user.response.TokenResponse;
import com.alchemist.bianca.entity.mail.VerifyCode;
import com.alchemist.bianca.entity.mail.VerifyCodeRepository;
import com.alchemist.bianca.entity.refresh_token.RefreshToken;
import com.alchemist.bianca.entity.refresh_token.RefreshTokenRepository;
import com.alchemist.bianca.entity.user.User;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.*;
import com.alchemist.bianca.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.refresh.exp}")
    private Long refreshExp;

    @Value("${spring.mail.username}")
    private String mailAddress;

    @Value("${spring.mail.title")
    private String title;

    @Value("${spring.mail.exp")
    private Long mailExp;

    @Value("${code.min}")
    private Integer min;

    @Value("${code.max}")
    private Integer max;

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final VerifyCodeRepository codeRepository;
    private final JavaMailSender mailSender;

    public ResponseEntity<TokenResponse> login(LoginRequest request) throws Exception {
        UserDetails user = userRepository.findById(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        String password = getEncrypt(request.getPassword());
        if (user.getPassword().equals(password)) {
            throw new MismatchedPassword();
        }

        TokenResponse token = getToken(request.getEmail());

        return new ResponseEntity<>(new TokenResponse(
                token.getAccessToken(),
                token.getRefreshToken(),
                token.getEmail()
        ), HttpStatus.OK);
    }

    public void signUp(SignUpRequest request) throws Exception {
        if (userRepository.findById(request.getEmail()).isPresent()) {
            throw new AlreadyExistEmailException();
        } else {
            userRepository.save(
                    User.builder()
                            .email(request.getEmail())
                            .password(getEncrypt(request.getPassword()))
                            .name(request.getName())
                            .build()
            );
        }
    }

    public void overlapEmail(EmailRequest request) {
        userRepository.findById(request.getEmail())
                .orElseThrow(AlreadyExistEmailException::new);
    }

    public void overlapName(NameRequest request) {
        userRepository.findByName(request.getName())
                .orElseThrow(AlreadyExistNameException::new);
    }

    public void getCode(MailSendRequest request) {
        String address = request.getEmail();
        String code = sendMail(address);
        codeRepository.findById(address)
                .or(() -> Optional.of(new VerifyCode(address, code, mailExp)))
                .ifPresent(certification -> codeRepository.save(certification.update(code, mailExp)));
    }

    public void checkCode(VerifyCodeRequest request) {
        VerifyCode code = codeRepository.findById(request.getEmail())
                .orElseThrow(InvalidCodeException::new);
        if (!code.getCode().equals(request.getCode())) {
            throw new UnlikeCodeException();
        }
    }

    public TokenResponse tokenRefresh(RefreshTokenRequest request) {
        if (jwtTokenProvider.validateToken(request.getRefreshToken())){
            return refreshTokenRepository.findByToken(request.getRefreshToken())
                    .map(token -> getToken(token.getEmail()))
                    .orElseThrow(ExpiredRefreshTokenException::new);
        }
        throw new InvalidTokenException();
    }

    private String sendMail(String email) {
        String code = Integer.toString(randomCode());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(mailAddress);
        message.setSubject(title);
        message.setText(code);
        mailSender.send(message);

        return code;
    }

    private TokenResponse getToken(String email) {
        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);
        refreshTokenRepository.findById(email)
                .or(() -> Optional.of(new RefreshToken(email, refreshToken, refreshExp)))
                .ifPresent(token -> refreshTokenRepository.save(token.update(refreshToken, refreshExp)));

        return new TokenResponse(accessToken, refreshToken, email);
    }

    private Integer randomCode() {
        return (int) (Math.random() * (max - min) + min);
    }

    private String getEncrypt(String password) throws Exception {
        StringBuffer sbuf = new StringBuffer();
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        mDigest.update(password.getBytes());

        byte[] msgStr = mDigest.digest() ;

        for(int i=0; i < msgStr.length; i++){
            byte tmpStrByte = msgStr[i];
            String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);

            sbuf.append(tmpEncTxt) ;
        }

        return sbuf.toString();
    }

}
