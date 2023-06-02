package com.artstore.microservices.application.userservice.mapper;


import com.artstore.microservices.application.userservice.DTO.SignUpDTO;
import com.artstore.microservices.application.userservice.DTO.UserDTO;
import com.artstore.microservices.application.userservice.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User signUpToUser(SignUpDTO signUpDTO) {
        User user = modelMapper.map(signUpDTO, User.class);
        user.setPassword(null);
        return user;
    }
}
