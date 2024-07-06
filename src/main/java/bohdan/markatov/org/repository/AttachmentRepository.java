package bohdan.markatov.org.repository;

import bohdan.markatov.org.model.Attachment;
import bohdan.markatov.org.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    boolean existsByTaskIdAndFileName(Long taskId, String fileName);

    boolean existsByTaskId(Long taskId);

    List<Attachment> findAllByTask(Task taskId);
}
