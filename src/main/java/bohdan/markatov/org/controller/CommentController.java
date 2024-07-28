package bohdan.markatov.org.controller;

import bohdan.markatov.org.dto.comment.CommentRequestDto;
import bohdan.markatov.org.dto.comment.CommentResponseDto;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.service.comment.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/task/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto create(Authentication authentication,
                                     @PathVariable Long taskId,
                                     @RequestBody @Valid CommentRequestDto dto) {
        return commentService.save((User) authentication.getPrincipal(), taskId, dto.getText());
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto update(Authentication authentication,
                                     @PathVariable Long commentId,
                                     @RequestBody @Valid CommentRequestDto dto) {
        return commentService.update(
                (User) authentication.getPrincipal(), commentId, dto.getText());
    }

    @GetMapping("/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> getAll(Authentication authentication,
                                           @PathVariable Long taskId) {
        return commentService.getAll((User) authentication.getPrincipal(), taskId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication,
                       @PathVariable Long commentId) {
        commentService.delete((User) authentication.getPrincipal(), commentId);
    }
}
