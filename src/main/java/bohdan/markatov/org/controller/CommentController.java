package bohdan.markatov.org.controller;

import bohdan.markatov.org.dto.comment.CommentResponseDto;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.service.comment.CommentService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                                     @RequestParam @NotNull @Size(max = 500) String text) {
        return commentService.save((User) authentication.getPrincipal(), taskId, text);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto update(Authentication authentication,
                                     @PathVariable Long commentId,
                                     @RequestParam @NotNull @Size(max = 500) String text) {
        return commentService.update((User) authentication.getPrincipal(), commentId, text);
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
