package com.artstore.microservices.application.userservice.DTO;

public record SignUpDTO (String fullName, String email, String login, char[] password){
}
