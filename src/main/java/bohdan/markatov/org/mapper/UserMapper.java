package bohdan.markatov.org.mapper;

import bohdan.markatov.org.config.MapperConfig;
import bohdan.markatov.org.dto.user.UserRegistrationRequestDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto dto);
}
