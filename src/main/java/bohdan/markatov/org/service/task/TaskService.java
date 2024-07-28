package bohdan.markatov.org.service.task;

import bohdan.markatov.org.dto.task.CreateTaskRequestDto;
import bohdan.markatov.org.dto.task.TaskResponseDto;
import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.Task;
import bohdan.markatov.org.model.User;
import java.util.List;

public interface TaskService {

    TaskResponseDto save(User manager, CreateTaskRequestDto dto);

    Task get(User user, Long taskId);

    List<TaskResponseDto> getAll(User manager, Long projectId);

    List<TaskResponseDto> getAll(User responsibleUser);

    TaskResponseDto updateStatus(User managerOrResponsibleUser, Long taskId, Task.Status status);

    void delete(User manager, Long taskId);

    void deleteAllByUserAndProject(User user, Project project);
}
