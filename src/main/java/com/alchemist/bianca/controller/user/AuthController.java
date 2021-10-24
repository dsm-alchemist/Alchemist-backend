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
    public String overlapEmail(@PathVariable("email") @Valid EmailRequest request) {
        return authService.overlapEmail(request);
    }

    @GetMapping("/reduplication/{name}")
    public String overlapName(@PathVariable("name") @Valid NameRequest request) {
        return authService.overlapName(request);
    }

    @PostMapping("/sms-certification/sends")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendCode(@RequestBody @Valid EmailRequest request) {
        authService.sendCode(request);
    }

    @GetMapping("/sms-certification/confirms")
    public String checkCode(@RequestParam("code") @Valid VerifyCodeRequest request) {
        return authService.checkCode(request);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping("/refresh")
    public TokenResponse tokenRefresh(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.tokenRefresh(request);
    }

    @DeleteMapping("/account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount() {
        authService.deleteAccount();
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        authService.logout();
    }
}
