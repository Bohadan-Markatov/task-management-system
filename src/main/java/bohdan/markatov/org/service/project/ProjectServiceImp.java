package bohdan.markatov.org.service.project;

import bohdan.markatov.org.dto.notification.Notification;
import bohdan.markatov.org.dto.notification.NotificationStatus;
import bohdan.markatov.org.dto.project.CreateProjectRequestDto;
import bohdan.markatov.org.dto.project.ProjectResponseDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.exception.EntityNotFoundException;
import bohdan.markatov.org.exception.NotUniqueValueException;
import bohdan.markatov.org.mapper.ProjectMapper;
import bohdan.markatov.org.mapper.UserMapper;
import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.repository.ProjectRepository;
import bohdan.markatov.org.service.notification.NotificationService;
import bohdan.markatov.org.service.task.TaskService;
import bohdan.markatov.org.service.user.UserService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final NotificationService notificationService;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private TaskService taskService;

    @Lazy
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectResponseDto save(User manager, CreateProjectRequestDto dto) {
        if (projectRepository.existsByNameAndManager(dto.getName(), manager)) {
            throw new NotUniqueValueException("A project with this name already exists");
        }
        Project project = projectMapper.toModel(dto);
        project.setManager(manager);
        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public Project get(User manager, Long projectId) {
        return getProjectAndVerifyOwnership(manager, projectId);
    }

    @Override
    public Project getWithTeamMembers(User manager, Long projectId) {
        return getProjectWithTeamMembersAndVerifyOwnership(manager, projectId);
    }

    @Override
    public List<ProjectResponseDto> getAll(User manager) {
        return projectRepository.findAllByManager(manager).stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto addTeamMember(User manager, Long projectId, String teamMemberEmail) {
        Project project = getProjectWithTeamMembersAndVerifyOwnership(manager, projectId);
        User teamMember = userService.get(teamMemberEmail);
        Set<User> teamMembers = project.getTeamMembers();
        if (manager.equals(teamMember) || teamMembers.contains(teamMember)) {
            throw new RuntimeException("This user is already in the project");
        }
        teamMembers.add(teamMember);
        project.setTeamMembers(teamMembers);
        projectRepository.save(project);
        notificationService.sendNotification(teamMemberEmail, Notification.builder()
                .message("You have been added to the " + project.getName() + " project.")
                .status(NotificationStatus.ADDED)
                .build());
        return userMapper.toDto(teamMember);
    }

    @Override
    public boolean isUserInProject(User user, Long projectId) {
        Project project = projectRepository.findByIdWithTeamMembers(projectId).orElseThrow(()
                -> new EntityNotFoundException("Can't find project by id: " + projectId));
        return project.getManager().equals(user) || project.getTeamMembers().contains(user);
    }

    @Override
    public void deleteTeamMember(User manager, Long projectId, Long teamMemberId) {
        Project project = getProjectWithTeamMembersAndVerifyOwnership(manager, projectId);
        User teamMember = userService.get(teamMemberId);
        Set<User> teamMembers = project.getTeamMembers();
        if (!teamMembers.contains(teamMember)) {
            throw new RuntimeException("This user is not in the project");
        }
        teamMembers.remove(teamMember);
        project.setTeamMembers(teamMembers);
        projectRepository.save(project);
        notificationService.sendNotification(teamMember.getEmail(), Notification.builder()
                .message("You have been removed from the " + project.getName() + " project.")
                .status(NotificationStatus.DELETED)
                .build());
        taskService.deleteAllByUserAndProject(teamMember, project);
    }

    @Override
    public void quitProject(User teamMember, Long projectId) {
        Project project = projectRepository.findByIdWithTeamMembers(projectId).orElseThrow(()
                -> new EntityNotFoundException("Can't find project by id: " + projectId));
        Set<User> teamMembers = project.getTeamMembers();
        if (!teamMembers.contains(teamMember)) {
            throw new RuntimeException("You are not participating in this project");
        }
        teamMembers.remove(teamMember);
        taskService.deleteAllByUserAndProject(teamMember, project);
        project.setTeamMembers(teamMembers);
        notificationService.sendNotification(project.getManager().getEmail(), Notification.builder()
                .message("Participant "
                        + teamMember.getFirstname()
                        + " " + teamMember.getLastname()
                        + " left the project")
                .status(NotificationStatus.DELETED)
                .build());
        projectRepository.save(project);
    }

    @Override
    public void delete(User manager, Long projectId) {
        Project project = getProjectAndVerifyOwnership(manager, projectId);
        projectRepository.delete(project);
    }

    private Project getProjectAndVerifyOwnership(User manager, Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty() || !project.get().getManager().equals(manager)) {
            throw new EntityNotFoundException("Can't find project by id: " + projectId);
        }
        return project.get();
    }

    private Project getProjectWithTeamMembersAndVerifyOwnership(User manager, Long projectId) {
        Optional<Project> project = projectRepository.findByIdWithTeamMembers(projectId);
        if (project.isEmpty() || !project.get().getManager().equals(manager)) {
            throw new EntityNotFoundException("Can't find project by id: " + projectId);
        }
        return project.get();
    }
}
