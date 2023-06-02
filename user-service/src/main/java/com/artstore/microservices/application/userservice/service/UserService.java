package com.artstore.microservices.application.userservice.service;

import com.artstore.microservices.application.userservice.DTO.CredentialsDTO;
import com.artstore.microservices.application.userservice.DTO.SignUpDTO;
import com.artstore.microservices.application.userservice.DTO.UserDTO;


public interface UserService {

    UserDTO login(CredentialsDTO credentialsDto);
    UserDTO register(SignUpDTO userDto);
    UserDTO findByLogin(String login);
}
