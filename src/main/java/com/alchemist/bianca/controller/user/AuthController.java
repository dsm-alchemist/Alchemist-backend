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

    @GetMapping("/reduplication/email/{email}")
    public String overlapEmail(@PathVariable("email") String email) {
        return authService.overlapEmail(email);
    }

    @GetMapping("/reduplication/name/{name}")
    public String overlapName(@PathVariable("name") String name) {
        return authService.overlapName(name);
    }

    @PostMapping("/sms-certification/sends")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendCode(@RequestBody @Valid EmailRequest request) {
        authService.sendCode(request);
    }

    @GetMapping("/sms-certification/confirms")
    public String checkCode(@RequestParam("code") String code, @RequestParam("email") String email) {
        return authService.checkCode(code, email);
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

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
