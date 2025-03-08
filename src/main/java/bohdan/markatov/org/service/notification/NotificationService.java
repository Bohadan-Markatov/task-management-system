package bohdan.markatov.org.service.notification;

import bohdan.markatov.org.dto.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String userEmail, Notification notification) {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!Sending notification to user {}", userEmail);
        messagingTemplate.convertAndSendToUser(userEmail, "/notifications", notification);
    }
}
