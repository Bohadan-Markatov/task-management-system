package bohdan.markatov.org.model;

import bohdan.markatov.org.config.ApplicationContextHolder;
import bohdan.markatov.org.service.dropbox.DropboxService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false, name = "task_id")
    @ManyToOne()
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Task task;
    @Column(nullable = false)
    private String dropboxFileId;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private LocalDateTime uploadDate;

    @Transient
    private DropboxService dropboxService;

    @PreRemove
    private void preRemove() {
        dropboxService = ApplicationContextHolder.getContext().getBean(DropboxService.class);
        dropboxService.deleteById(dropboxFileId);
    }
}
