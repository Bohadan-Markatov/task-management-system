package bohdan.markatov.org.dto.project;

import bohdan.markatov.org.dto.user.UserResponseDto;
import java.util.Set;
import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private UserResponseDto manager;
    private Set<UserResponseDto> teamMembers;
}
