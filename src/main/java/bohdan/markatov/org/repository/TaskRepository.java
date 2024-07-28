package bohdan.markatov.org.repository;

import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.Task;
import bohdan.markatov.org.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProject(Project project);

    List<Task> findAllByResponsibleUser(User user);

    void deleteAllByProject(Project project);

    void deleteAllByResponsibleUserAndProject(User user, Project project);
}
