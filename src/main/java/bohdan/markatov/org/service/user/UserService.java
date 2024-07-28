package bohdan.markatov.org.service.user;

import bohdan.markatov.org.dto.user.UserLoginResponseDto;
import bohdan.markatov.org.dto.user.UserRegistrationRequestDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.exception.RegistrationException;
import bohdan.markatov.org.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {

    UserResponseDto registerNewUserAccount(UserRegistrationRequestDto dto)
            throws RegistrationException;

    User get(Long userId);

    User get(String email);

    UserLoginResponseDto authenticateWithOAuth2(OAuth2User auth2User);

    void enableUserAccount(User user);
}
