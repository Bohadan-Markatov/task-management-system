package bohdan.markatov.org.mapper.impl;

import bohdan.markatov.org.dto.project.CreateProjectRequestDto;
import bohdan.markatov.org.dto.project.ProjectResponseDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.mapper.ProjectMapper;
import bohdan.markatov.org.mapper.UserMapper;
import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-11T22:07:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    private final UserMapper userMapper;

    @Autowired
    public ProjectMapperImpl(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    @Override
    public ProjectResponseDto toDto(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        if ( project.getId() != null ) {
            projectResponseDto.setId( project.getId() );
        }
        if ( project.getName() != null ) {
            projectResponseDto.setName( project.getName() );
        }
        if ( project.getDescription() != null ) {
            projectResponseDto.setDescription( project.getDescription() );
        }
        if ( project.getManager() != null ) {
            projectResponseDto.setManager( userMapper.toDto( project.getManager() ) );
        }
        Set<UserResponseDto> set = userSetToUserResponseDtoSet( project.getTeamMembers() );
        if ( set != null ) {
            projectResponseDto.setTeamMembers( set );
        }

        return projectResponseDto;
    }

    @Override
    public Project toModel(CreateProjectRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Project project = new Project();

        if ( dto.getName() != null ) {
            project.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            project.setDescription( dto.getDescription() );
        }

        return project;
    }

    protected Set<UserResponseDto> userSetToUserResponseDtoSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserResponseDto> set1 = new LinkedHashSet<UserResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userMapper.toDto( user ) );
        }

        return set1;
    }
}
