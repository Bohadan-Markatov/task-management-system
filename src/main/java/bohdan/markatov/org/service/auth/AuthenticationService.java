package bohdan.markatov.org.service.auth;

import bohdan.markatov.org.dto.user.UserLoginRequestDto;
import bohdan.markatov.org.dto.user.UserLoginResponseDto;
import bohdan.markatov.org.model.User;

public interface AuthenticationService {

    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);

    void saveConfirmationTokenAndSendEmail(User user);

    String confirmVerificationToken(String token);
}
