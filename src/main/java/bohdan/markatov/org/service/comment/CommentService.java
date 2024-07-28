package bohdan.markatov.org.service.comment;

import bohdan.markatov.org.dto.comment.CommentResponseDto;
import bohdan.markatov.org.model.User;
import java.util.List;

public interface CommentService {

    CommentResponseDto save(User author, Long taskId, String text);

    CommentResponseDto update(User author, Long commentId, String text);

    List<CommentResponseDto> getAll(User user, Long taskId);

    void delete(User author, Long commentId);
}
