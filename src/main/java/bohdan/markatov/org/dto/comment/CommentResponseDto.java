package bohdan.markatov.org.dto.comment;

import bohdan.markatov.org.dto.user.UserResponseDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String text;
    private UserResponseDto author;
    private Long taskId;
    private LocalDateTime publicationDate;
    private boolean isChanged;
}
