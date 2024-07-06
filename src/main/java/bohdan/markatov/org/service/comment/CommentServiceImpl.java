package bohdan.markatov.org.service.comment;

import bohdan.markatov.org.dto.comment.CommentResponseDto;
import bohdan.markatov.org.exception.EntityNotFoundException;
import bohdan.markatov.org.mapper.CommentMapper;
import bohdan.markatov.org.model.Comment;
import bohdan.markatov.org.model.Task;
import bohdan.markatov.org.model.User;
import bohdan.markatov.org.repository.CommentRepository;
import bohdan.markatov.org.service.task.TaskService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskService taskService;

    @Override
    public CommentResponseDto save(User author, Long taskId, String text) {
        Task task = taskService.get(author, taskId);
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(author);
        comment.setTask(task);
        comment.setPublicationDate(LocalDateTime.now());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDto update(User author, Long commentId, String text) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty() || !comment.get().getAuthor().equals(author)) {
            throw new EntityNotFoundException("Can't find comment by id: " + comment);
        }
        Comment updated = comment.get();
        updated.setText(text);
        updated.setPublicationDate(LocalDateTime.now());
        updated.setChanged(true);
        return commentMapper.toDto(commentRepository.save(updated));
    }

    @Override
    public List<CommentResponseDto> getAll(User user, Long taskId) {
        Task task = taskService.get(user, taskId);
        return commentRepository.findAllByTask(task).stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public void delete(User author, Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty() || !comment.get().getAuthor().equals(author)) {
            throw new EntityNotFoundException("Can't find comment by id: " + commentId);
        }
        commentRepository.delete(comment.get());
    }
}
