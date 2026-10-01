package com.nexocercaati.service;

import com.nexocercaati.request.LoginRequest;
import com.nexocercaati.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
