package bohdan.markatov.org.repository;

import bohdan.markatov.org.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(Role.RoleName name);
}
