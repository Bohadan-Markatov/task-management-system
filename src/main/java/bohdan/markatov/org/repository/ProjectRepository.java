package bohdan.markatov.org.repository;

import bohdan.markatov.org.model.Project;
import bohdan.markatov.org.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByManager(User manager);

    @Query("""
      SELECT project
      FROM Project project
      LEFT JOIN FETCH project.teamMembers
      WHERE project.id = :projectId
            """)
    Optional<Project> findByIdWithTeamMembers(Long projectId);

    boolean existsByNameAndManager(String name, User manager);
}
