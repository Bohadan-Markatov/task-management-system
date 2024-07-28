package bohdan.markatov.org.dto.task;

import bohdan.markatov.org.model.Task;
import lombok.Data;

@Data
public class UpdateTaskStatusRequestDto {
    private Task.Status status;
}
