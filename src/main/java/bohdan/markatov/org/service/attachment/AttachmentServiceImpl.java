package bohdan.markatov.org.service.attachment;

import bohdan.markatov.org.dto.attachment.AttachmentResponseDto;
import bohdan.markatov.org.exception.EntityNotFoundException;
import bohdan.markatov.org.exception.NotUniqueValueException;
import bohdan.markatov.org.mapper.AttachmentMapper;
import bohdan.markatov.org.model.Attachment;
import bohdan.markatov.org.model.Task;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.repository.AttachmentRepository;
import bohdan.markatov.org.service.dropbox.DropboxService;
import bohdan.markatov.org.service.task.TaskService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;
    private final DropboxService dropboxService;
    private final TaskService taskService;

    @Override
    public AttachmentResponseDto save(User user, Long taskId, MultipartFile file) {
        if (attachmentRepository.existsByTaskIdAndFileName(taskId, file.getOriginalFilename())) {
            throw new NotUniqueValueException("File name must be unique");
        }
        Task task = taskService.get(user, taskId);
        Attachment attachment = saveNewAttachment(file, task);
        AttachmentResponseDto responseDto = attachmentMapper.toDto(attachment);
        responseDto.setFilePublicLink(
                dropboxService.getFilePublicLink(attachment.getDropboxFileId()));
        return responseDto;
    }

    @Override
    public List<AttachmentResponseDto> getAll(User user, Long taskId) {
        Task task = taskService.get(user, taskId);
        if (attachmentRepository.existsByTaskId(taskId)) {
            return attachmentRepository.findAllByTask(task).stream()
                    .map(this::mapToDtoAndSetFileLink)
                    .toList();
        }
        throw new EntityNotFoundException("Can't find attachments");
    }

    @Override
    public void delete(User user, Long attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(()
                -> new EntityNotFoundException("Can't find attachment by id: " + attachmentId));
        taskService.get(user, attachment.getTask().getId());
        attachmentRepository.delete(attachment);
    }

    private String getFilePublicLink(String dropBoxFileId) {
        return dropboxService.getFilePublicLink(dropBoxFileId);
    }

    private String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    private String getFileName(MultipartFile file) {
        return file.getOriginalFilename();
    }

    private Attachment saveNewAttachment(MultipartFile file, Task task) {
        Attachment attachment = new Attachment();
        attachment.setTask(task);
        attachment.setDropboxFileId(dropboxService.uploadFile(file, task.getId()));
        attachment.setFileName(getFileName(file));
        attachment.setUploadDate(LocalDateTime.now());
        return attachmentRepository.save(attachment);
    }

    private AttachmentResponseDto mapToDtoAndSetFileLink(Attachment attachment) {
        AttachmentResponseDto responseDto = attachmentMapper.toDto(attachment);
        responseDto.setFilePublicLink(getFilePublicLink(attachment.getDropboxFileId()));
        return responseDto;
    }
}
