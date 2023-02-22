package com.chandankumar.springbootblogapp.service;

import com.chandankumar.springbootblogapp.dto.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);

}
