package bohdan.markatov.org.dto.user;

import bohdan.markatov.org.util.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(
        firstField = "password",
        secondField = "repeatPassword",
        message = "Password and repeat password must match"
)
public class UserRegistrationRequestDto {
    @Email(message = "Wrong email format")
    @NotNull(message = "Email is mandatory")
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "The password length must be from 8 to 30 characters")
    private String password;
    @NotNull(message = "Repeat password is mandatory")
    @NotEmpty(message = "Repeat password is mandatory")
    @Size(min = 8, max = 30, message = "The repeat password length must be from 8 to 30 characters")
    private String repeatPassword;
    @NotNull(message = "Firstname is mandatory")
    @NotEmpty(message = "Firstname is mandatory")
    @Size(min = 1, max = 100, message = "The firstname length must be from 1 to 100 characters")
    private String firstname;
    @Size(max = 100, message = "Last name length should not exceed 100 characters")
    private String lastname;
}
