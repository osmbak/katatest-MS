package com.kataTest.back.rest;

import com.kataTest.back.DTO.AuthResponseDto;
import com.kataTest.back.DTO.LoginRequestDto;
import com.kataTest.back.DTO.RegisterRequestDto;
import com.kataTest.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.register(registerRequestDto));
    }

    @GetMapping("/userConnected")
    public ResponseEntity<String> getUser(@RequestBody RegisterRequestDto registerRequestDto) {
        String user = authService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

}
