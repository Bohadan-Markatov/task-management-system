package bohdan.markatov.org.controller;

import bohdan.markatov.org.dto.task.CreateTaskRequestDto;
import bohdan.markatov.org.dto.task.TaskResponseDto;
import bohdan.markatov.org.dto.task.UpdateTaskStatusRequestDto;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.service.task.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(Authentication authentication,
                                  @RequestBody @Valid CreateTaskRequestDto dto) {
        return taskService.save((User) authentication.getPrincipal(), dto);
    }

    @GetMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDto> getAll(Authentication authentication,
                                                       @PathVariable @NotNull Long projectId) {
        return taskService.getAll((User) authentication.getPrincipal(), projectId);
    }

    @GetMapping("/responsible")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDto> getAll(Authentication authentication) {
        return taskService.getAll((User) authentication.getPrincipal());
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDto updateStatus(Authentication authentication,
                                 @PathVariable @NotNull Long taskId,
                                 @RequestBody @NotNull UpdateTaskStatusRequestDto status) {
        return taskService.updateStatus(
                (User) authentication.getPrincipal(), taskId, status.getStatus());
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication,
                       @PathVariable @NotNull Long taskId) {
        taskService.delete((User) authentication.getPrincipal(), taskId);
    }
}
