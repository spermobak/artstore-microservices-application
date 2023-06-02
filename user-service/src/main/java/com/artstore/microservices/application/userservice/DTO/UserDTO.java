package com.artstore.microservices.application.userservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    @Size(max = 100)
    @NotBlank
    private String fullName;
    @Size(max = 100)
    @NotBlank
    private String email;
    @Size(max = 100)
    @NotBlank
    private String login;
    private String token;

}
