package bohdan.markatov.org.dto.task;

import bohdan.markatov.org.model.Task;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateTaskRequestDto {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    private String description;
    private Task.Status status;
    private Task.Priority priority;
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    @NotNull(message = "Responsible User ID cannot be null")
    private Long responsibleUserId;
    @NotNull(message = "Deadline cannot be null")
    private LocalDateTime deadline;
}
