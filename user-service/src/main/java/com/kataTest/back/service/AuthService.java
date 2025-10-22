package com.kataTest.back.service;



import com.kataTest.back.DTO.AuthResponseDto;
import com.kataTest.back.DTO.LoginRequestDto;
import com.kataTest.back.DTO.RegisterRequestDto;
import com.kataTest.back.security.JwtUtil;
import com.kataTest.back.enteties.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kataTest.back.repositoy.UserRepository;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
    return null;
  }

  public AuthResponseDto login(LoginRequestDto loginRequestDto) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
    );
    jwtUtil.generateToken(loginRequestDto.getEmail());
    /*User user = userRepository.findByEmail(request.getEmail()).orElseThrow();*/
    var jwt = jwtUtil.generateToken(loginRequestDto.getEmail());
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
    var jwt = jwtUtil.generateToken(request.getEmail());
    return AuthResponseDto.builder()
      .token(jwt)
      .build();
  }
}
