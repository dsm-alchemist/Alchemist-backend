package com.alchemist.bianca.controller.user;

import com.alchemist.bianca.dto.user.request.*;
import com.alchemist.bianca.dto.user.response.TokenResponse;
import com.alchemist.bianca.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Valid SignUpRequest request) {
        authService.signUp(request);
    }

    @GetMapping("/reduplication/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overlapEmail(@PathVariable("email") @Valid EmailRequest request) {
        authService.overlapEmail(request);
    }

    @GetMapping("/reduplication/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overlapName(@PathVariable("name") @Valid NameRequest request) {
        authService.overlapName(request);
    }

    @PostMapping("/sms-certification/sends")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendCode(@RequestBody @Valid EmailRequest request) {
        authService.sendCode(request);
    }

    @GetMapping("/sms-certification/confirms")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkCode(@RequestParam("code") @Valid VerifyCodeRequest request) {
        authService.checkCode(request);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public TokenResponse tokenRefresh(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.tokenRefresh(request);
    }

    @DeleteMapping("/account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount() {
        authService.deleteAccount();
    }
}
