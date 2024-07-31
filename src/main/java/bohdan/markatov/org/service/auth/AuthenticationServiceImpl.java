package bohdan.markatov.org.service.auth;

import bohdan.markatov.org.dto.user.UserLoginRequestDto;
import bohdan.markatov.org.dto.user.UserLoginResponseDto;
import bohdan.markatov.org.model.EmailVerificationToken;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.repository.VerificationTokenRepository;
import bohdan.markatov.org.service.user.UserService;
import bohdan.markatov.org.util.jwt.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserService userService;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${app.mail}")
    private String sender;

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(), requestDto.getPassword())
        );

        String token = jwtUtil.generateToken(requestDto.getEmail());
        return new UserLoginResponseDto(token);
    }

    @Override
    public void saveConfirmationTokenAndSendEmail(User user) {
        EmailVerificationToken verificationToken = new EmailVerificationToken();
        String token = UUID.randomUUID().toString();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        mailSender.send(createConfirmationMessage(user.getEmail(), token));
    }

    @Override
    public String confirmVerificationToken(String token) {
        Optional<EmailVerificationToken> verificationToken
                = verificationTokenRepository.findByToken(token);
        if (verificationToken.isEmpty()) {
            return "invalid_token";
        }
        if ((verificationToken.get().getExpiryDate().getTime() - System.currentTimeMillis()) <= 0) {
            saveConfirmationTokenAndSendEmail(verificationToken.get().getUser());
            return "token_expired";
        }
        userService.enableUserAccount(verificationToken.get().getUser());
        return "successful_confirmation";
    }

    private MimeMessage createConfirmationMessage(String recipientEmail, String token) {
        MimeMessage message = mailSender.createMimeMessage();
        Context context = new Context();
        Map<String, Object> properties = new HashMap<>();
        properties.put("token", token);
        context.setVariables(properties);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name());
            helper.setFrom(sender);
            helper.setTo(recipientEmail);
            helper.setSubject("Registration Confirmation");
            helper.setText(templateEngine.process("activate_account", context), true);
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException("Can't create confirmation message");
        }
    }
}
