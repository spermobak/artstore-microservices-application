package com.artstore.microservices.application.userservice.service;

import com.artstore.microservices.application.userservice.DTO.CredentialsDTO;
import com.artstore.microservices.application.userservice.DTO.SignUpDTO;
import com.artstore.microservices.application.userservice.DTO.UserDTO;
import com.artstore.microservices.application.userservice.exception.AppException;
import com.artstore.microservices.application.userservice.mapper.UserMapper;
import com.artstore.microservices.application.userservice.model.User;
import com.artstore.microservices.application.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDTO login(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByLogin(credentialsDTO.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()), user.getPassword())) {
            return userMapper.toUserDTO(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDTO register(SignUpDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByLogin(userDTO.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.password())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDTO(user);
    }

}