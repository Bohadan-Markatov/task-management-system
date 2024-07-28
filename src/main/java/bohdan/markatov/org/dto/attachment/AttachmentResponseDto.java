package bohdan.markatov.org.dto.attachment;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AttachmentResponseDto {
    private Long id;
    private Long taskId;
    private String taskName;
    private String fileName;
    private String filePublicLink;
    private LocalDateTime uploadDate;
}
