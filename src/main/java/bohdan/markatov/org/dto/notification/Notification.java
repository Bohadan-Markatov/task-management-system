package bohdan.markatov.org.dto.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {
    private NotificationStatus status;
    private String message;
}
