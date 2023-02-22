package com.chandankumar.springbootblogapp.service;

import com.chandankumar.springbootblogapp.dto.LoginDto;
import com.chandankumar.springbootblogapp.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
