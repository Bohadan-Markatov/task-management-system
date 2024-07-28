package bohdan.markatov.org.service.attachment;

import bohdan.markatov.org.dto.attachment.AttachmentResponseDto;
import bohdan.markatov.org.model.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    AttachmentResponseDto save(User user, Long taskId, MultipartFile file);

    List<AttachmentResponseDto> getAll(User user, Long taskId);

    void delete(User user, Long id);
}
