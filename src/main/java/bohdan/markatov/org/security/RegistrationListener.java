package bohdan.markatov.org.security;

import bohdan.markatov.org.model.User;
import bohdan.markatov.org.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final AuthenticationService authenticationService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        authenticationService.saveConfirmationTokenAndSendEmail(user);
    }
}
