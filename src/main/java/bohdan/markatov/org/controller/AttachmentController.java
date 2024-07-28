package bohdan.markatov.org.controller;

import bohdan.markatov.org.dto.attachment.AttachmentResponseDto;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.service.attachment.AttachmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping(path = "/task/{taskId}", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public AttachmentResponseDto create(Authentication authentication,
                                        @PathVariable Long taskId,
                                        @RequestParam("file")MultipartFile file) {
        return attachmentService.save((User) authentication.getPrincipal(), taskId, file);
    }

    @GetMapping("/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AttachmentResponseDto> getAll(Authentication authentication,
                                              @PathVariable Long taskId) {
        return attachmentService.getAll((User) authentication.getPrincipal(), taskId);
    }

    @DeleteMapping("/{attachmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication,
                       @PathVariable Long attachmentId) {
        attachmentService.delete((User) authentication.getPrincipal(), attachmentId);
    }
}
