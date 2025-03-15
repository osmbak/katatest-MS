package com.katatest.service;


import com.kataTest.back.dto.auth.AuthenticationRequest;
import com.kataTest.back.dto.auth.AuthenticationResponse;
import com.kataTest.back.dto.auth.RegisterRequest;


public interface AuthentificationService {
    public String register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
