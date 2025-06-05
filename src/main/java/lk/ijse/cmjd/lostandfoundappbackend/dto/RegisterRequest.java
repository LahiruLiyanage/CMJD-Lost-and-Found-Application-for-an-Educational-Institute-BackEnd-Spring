package lk.ijse.cmjd.lostandfoundappbackend.dto;

import lk.ijse.cmjd.lostandfoundappbackend.enums.UserRole;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRole role = UserRole.USER;
}
