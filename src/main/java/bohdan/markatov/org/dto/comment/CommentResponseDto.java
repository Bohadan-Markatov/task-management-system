package bohdan.markatov.org.dto.comment;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String text;
    private Long authorId;
    private Long taskId;
    private LocalDateTime publicationDate;
    private boolean isChanged;
}
