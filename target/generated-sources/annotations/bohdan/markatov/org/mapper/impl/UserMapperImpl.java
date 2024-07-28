package bohdan.markatov.org.mapper.impl;

import bohdan.markatov.org.dto.user.UserRegistrationRequestDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.mapper.UserMapper;
import bohdan.markatov.org.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T01:33:58+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        if ( user.getId() != null ) {
            userResponseDto.setId( user.getId() );
        }
        if ( user.getEmail() != null ) {
            userResponseDto.setEmail( user.getEmail() );
        }
        if ( user.getFirstname() != null ) {
            userResponseDto.setFirstname( user.getFirstname() );
        }
        if ( user.getLastname() != null ) {
            userResponseDto.setLastname( user.getLastname() );
        }

        return userResponseDto;
    }

    @Override
    public User toModel(UserRegistrationRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getFirstname() != null ) {
            user.setFirstname( dto.getFirstname() );
        }
        if ( dto.getLastname() != null ) {
            user.setLastname( dto.getLastname() );
        }

        return user;
    }
}
