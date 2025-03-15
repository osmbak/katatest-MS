package com.katatest.service.impl;/*
package com.kataTest.back.service.impl;


import com.kataTest.back.dto.auth.AuthenticationRequest;
import com.kataTest.back.dto.auth.AuthenticationResponse;
import com.kataTest.back.dto.auth.RegisterRequest;
import com.kataTest.back.enteties.User;
import com.kataTest.back.repositoy.UserRepository;
import com.kataTest.back.service.AuthentificationService;
import com.kataTest.back.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthentificationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) {
        String role = "admin";
        var user = User.builder()
                .firstname(request.getFirstname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        repository.save(user);
        System.out.println("register ss");
        */
/*var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();*//*

        return "OK";
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        System.out.println("login web");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
*/
