package com.artstore.microservices.application.userservice.controller;


import com.artstore.microservices.application.userservice.DTO.CredentialsDTO;
import com.artstore.microservices.application.userservice.DTO.SignUpDTO;
import com.artstore.microservices.application.userservice.DTO.UserDTO;
import com.artstore.microservices.application.userservice.config.UserAuthenticationProvider;
import com.artstore.microservices.application.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController("/artStore")
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid CredentialsDTO credentialsDto) {
        UserDTO userDTO = userService.login(credentialsDto);
        userDTO.setToken(userAuthenticationProvider.createToken(userDTO));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid SignUpDTO user) {
        UserDTO createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}