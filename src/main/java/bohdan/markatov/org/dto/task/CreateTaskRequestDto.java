package bohdan.markatov.org.dto.task;

import bohdan.markatov.org.model.Task;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateTaskRequestDto {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    @Size(min = 1, max = 500)
    private String description;
    private Task.Status status;
    private Task.Priority priority;
    @NotNull
    private Long projectId;
    @NotNull
    private Long responsibleUserId;
    @NotNull
    private LocalDateTime deadline;
}
