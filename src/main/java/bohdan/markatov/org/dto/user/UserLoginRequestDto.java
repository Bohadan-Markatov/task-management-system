package bohdan.markatov.org.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email(message = "Wrong email format")
    @NotNull(message = "Email is mandatory")
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "The password length must be from 8 to 30 characters")
    private String password;
}
