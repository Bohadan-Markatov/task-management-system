package bohdan.markatov.org.repository;

import bohdan.markatov.org.model.Comment;
import bohdan.markatov.org.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTask(Task task);
}
