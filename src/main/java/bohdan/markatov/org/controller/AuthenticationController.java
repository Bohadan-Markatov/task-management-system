package bohdan.markatov.org.controller;

import bohdan.markatov.org.dto.user.UserLoginRequestDto;
import bohdan.markatov.org.dto.user.UserLoginResponseDto;
import bohdan.markatov.org.dto.user.UserRegistrationRequestDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.exception.RegistrationException;
import bohdan.markatov.org.service.auth.AuthenticationServiceImpl;
import bohdan.markatov.org.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationServiceImpl authenticationService;
    private final SpringTemplateEngine templateEngine;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @GetMapping("/oauth2-login")
    public void oauth2LoginSuccess(HttpServletResponse response,
                                   @AuthenticationPrincipal OAuth2User auth2User)
            throws IOException {
        String token = userService.authenticateWithOAuth2(auth2User).getToken();
        response.sendRedirect("http://localhost/oauth2/callback?token=" + token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.registerNewUserAccount(requestDto);
    }

    @GetMapping("/confirm-email")
    public String confirmEmail(@RequestParam("token") @NotNull String token) {
        return templateEngine.process(authenticationService.confirmVerificationToken(token),
                new Context());
    }
}
