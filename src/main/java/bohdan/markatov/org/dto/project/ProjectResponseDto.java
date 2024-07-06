package bohdan.markatov.org.dto.project;

import bohdan.markatov.org.dto.user.UserResponseDto;
import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private UserResponseDto manager;
}
