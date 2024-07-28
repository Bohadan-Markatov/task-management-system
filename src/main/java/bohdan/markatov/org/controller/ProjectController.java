package bohdan.markatov.org.controller;

import bohdan.markatov.org.dto.project.AddTeamMemberRequestDto;
import bohdan.markatov.org.dto.project.CreateProjectRequestDto;
import bohdan.markatov.org.dto.project.ProjectResponseDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.service.project.ProjectService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDto create(Authentication authentication,
                                     @RequestBody @Valid CreateProjectRequestDto dto) {
        return projectService.save((User) authentication.getPrincipal(), dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProjectResponseDto> getAll(Authentication authentication) {
        return projectService.getAll((User) authentication.getPrincipal());
    }

    @PutMapping("/{projectId}/team-members")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto addTeamMember(Authentication authentication,
                                         @PathVariable Long projectId,
                                         @RequestBody @Valid AddTeamMemberRequestDto dto) {
        return projectService.addTeamMember(
                (User) authentication.getPrincipal(), projectId, dto.getEmail());
    }

    @DeleteMapping("/{projectId}/team-members")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeamMember(Authentication authentication,
                              @PathVariable Long projectId,
                              @RequestParam @NotNull Long teamMemberId) {
        projectService.deleteTeamMember(
                (User) authentication.getPrincipal(), projectId, teamMemberId);
    }

    @DeleteMapping("/{projectId}/quit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void quitProject(Authentication authentication,
                            @PathVariable @NotNull Long projectId) {
        projectService.quitProject((User) authentication.getPrincipal(), projectId);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication,
                       @PathVariable @NotNull Long projectId) {
        projectService.delete((User) authentication.getPrincipal(), projectId);
    }
}
