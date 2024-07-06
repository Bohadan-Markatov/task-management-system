package bohdan.markatov.org.dto.task;

import bohdan.markatov.org.dto.project.ProjectResponseDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.model.Task;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String name;
    private String description;
    private Task.Status status;
    private Task.Priority priority;
    private ProjectResponseDto project;
    private UserResponseDto responsibleUser;
    private LocalDateTime deadline;
}
