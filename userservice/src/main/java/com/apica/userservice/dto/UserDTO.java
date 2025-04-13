package com.apica.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO used for user registration and update operations.
 */
@Data
public class UserDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    /**
     * Optional set of role names, e.g., ["USER"], ["ADMIN"]
     * This is typically optional and can be set by the backend.
     */
    private Set<String> roles = new HashSet<>();

    public Set<String> getRoles() {
        return roles == null ? Collections.emptySet() : roles;
    }
}