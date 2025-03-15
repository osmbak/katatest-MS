package com.katatest.service;


import com.kataTest.back.dto.auth.AuthResponseDto;
import com.kataTest.back.dto.auth.LoginRequestDto;
import com.kataTest.back.dto.auth.RegisterRequestDto;
import com.kataTest.back.enteties.User;
import com.kataTest.back.repositoy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final com.kataTest.back.service.JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponseDto login(LoginRequestDto request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwt = jwtService.generateToken(user);
    return AuthResponseDto.builder()
      .token(jwt)
      .build();
  }

  public AuthResponseDto register(RegisterRequestDto request) {
    Optional<User> userExist =  userRepository.findByEmail(request.getEmail());
    var user = User.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(com.kataTest.back.enteties.Role.USER)
      .build();
      userRepository.save(user);
    var jwt = jwtService.generateToken(user);
    return AuthResponseDto.builder()
      .token(jwt)
      .build();
  }
}
