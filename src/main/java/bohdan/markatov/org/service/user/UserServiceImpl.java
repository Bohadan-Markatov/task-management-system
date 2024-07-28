package bohdan.markatov.org.service.user;

import bohdan.markatov.org.dto.user.UserLoginResponseDto;
import bohdan.markatov.org.dto.user.UserRegistrationRequestDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.exception.EntityNotFoundException;
import bohdan.markatov.org.exception.RegistrationException;
import bohdan.markatov.org.mapper.UserMapper;
import bohdan.markatov.org.model.Role;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.repository.RoleRepository;
import bohdan.markatov.org.repository.UserRepository;
import bohdan.markatov.org.security.OnRegistrationCompleteEvent;
import bohdan.markatov.org.util.jwt.JwtUtil;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto registerNewUserAccount(UserRegistrationRequestDto dto)
            throws RegistrationException {
        checkEmailAvailability(dto.getEmail());
        User user = userMapper.toModel(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName(Role.RoleName.USER)));
        user = userRepository.save(user);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
        return userMapper.toDto(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Can't find user by id: " + id));
    }

    @Override
    public User get(String email) {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("Can't find user by email: " + email));
    }

    @Override
    public UserLoginResponseDto authenticateWithOAuth2(OAuth2User auth2User) {
        String email = auth2User.getAttribute("email");
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setEmail(email);
            user.setFirstname(auth2User.getAttribute("given_name"));
            user.setRoles(Set.of(roleRepository.findByName(Role.RoleName.USER)));
            user.setLastname(auth2User.getAttribute("family_name"));
            user.setEnabled(true);
            userRepository.save(user);
        }
        return new UserLoginResponseDto(jwtUtil.generateToken(email));
    }

    private void checkEmailAvailability(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RegistrationException("Email: " + email + " is already used");
        }
    }

    @Override
    public void enableUserAccount(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
