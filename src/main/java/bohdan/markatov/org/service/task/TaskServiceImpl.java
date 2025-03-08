package bohdan.markatov.org.service.task;

import bohdan.markatov.org.dto.notification.Notification;
import bohdan.markatov.org.dto.notification.NotificationStatus;
import bohdan.markatov.org.dto.task.CreateTaskRequestDto;
import bohdan.markatov.org.dto.task.TaskResponseDto;
import bohdan.markatov.org.exception.EntityNotFoundException;
import bohdan.markatov.org.mapper.TaskMapper;
import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.Task;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.repository.TaskRepository;
import bohdan.markatov.org.service.notification.NotificationService;
import bohdan.markatov.org.service.project.ProjectService;
import bohdan.markatov.org.service.user.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final NotificationService notificationService;
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDto save(User manager, CreateTaskRequestDto dto) {
        Task task = taskMapper.toModel(dto);
        Project project = projectService.getWithTeamMembers(manager, dto.getProjectId());
        User user = userService.get(dto.getResponsibleUserId());
        if (!project.getTeamMembers().contains(user)) {
            throw new EntityNotFoundException("Can't find user by id: "
                    + dto.getResponsibleUserId());
        }
        task.setProject(project);
        task.setResponsibleUser(user);
        notificationService.sendNotification(user.getEmail(),
                Notification.builder()
                        .status(NotificationStatus.ADDED)
                        .message("You have been assigned a new task for project "
                                + project.getName())
                        .build());
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public Task get(User user, Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty() || !projectService.isUserInProject(
                user, task.get().getProject().getId())) {
            throw new EntityNotFoundException("Can't find task by id: " + taskId);
        }
        return task.get();
    }

    @Override
    public List<TaskResponseDto> getAll(User manager, Long projectId) {
        Project project = projectService.get(manager, projectId);
        return taskRepository.findAllByProject(project).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public List<TaskResponseDto> getAll(User responsibleUser) {
        return taskRepository.findAllByResponsibleUser(responsibleUser).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskResponseDto updateStatus(User user, Long taskId, Task.Status status) {
        Task task = taskRepository.findById(taskId).orElseThrow(()
                -> new EntityNotFoundException("Can't find task by id: " + taskId));
        Project project = task.getProject();
        if (projectService.isUserInProject(user, project.getId())) {
            task.setStatus(status);
            if (status == Task.Status.COMPLETED) {
                notificationService.sendNotification(project.getManager().getEmail(),
                        Notification.builder()
                                .status(NotificationStatus.INFO)
                                .message("User "
                                        + task.getResponsibleUser().getFirstname()
                                        + " "
                                        + task.getResponsibleUser().getLastname()
                                        + " has completed task "
                                        + task.getName())
                                .build());
            }
            return taskMapper.toDto(taskRepository.save(task));
        }
        throw new EntityNotFoundException("Can't find task by id: " + taskId);
    }

    @Override
    public void delete(User manager, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()
                -> new EntityNotFoundException("Can't find task by id: " + taskId));
        if (!task.getResponsibleUser().equals(manager)) {
            projectService.get(manager, task.getProject().getId());
        }
        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public void deleteAllByUserAndProject(User user, Project project) {
        taskRepository.deleteAllByResponsibleUserAndProject(user, project);
    }
}
