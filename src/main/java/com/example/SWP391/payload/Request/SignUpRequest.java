package com.example.SWP391.Payload.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull(message = "Email cant null")
    @NotBlank(message = "Email not blank")
    @Email
    private String email;

    @NotNull(message = "Password cant null")
    @NotBlank(message = "Password not blank")
    private String password;

}
