package bohdan.markatov.org.service.project;

import bohdan.markatov.org.dto.project.CreateProjectRequestDto;
import bohdan.markatov.org.dto.project.ProjectResponseDto;
import bohdan.markatov.org.dto.user.UserResponseDto;
import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.User;
import java.util.List;

public interface ProjectService {

    ProjectResponseDto save(User manager, CreateProjectRequestDto dto);

    Project get(User manager, Long projectId);

    Project getWithTeamMembers(User manager, Long projectId);

    List<ProjectResponseDto> getAll(User manager);

    UserResponseDto addTeamMember(User manager, Long projectId, String teamMemberEmail);

    boolean isUserInProject(User user, Long projectId);

    void deleteTeamMember(User manager, Long projectId, Long teamMemberId);

    void quitProject(User teamMember, Long projectId);

    void delete(User manager, Long projectId);
}
