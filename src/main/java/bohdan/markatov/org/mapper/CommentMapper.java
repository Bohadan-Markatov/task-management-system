package bohdan.markatov.org.mapper;

import bohdan.markatov.org.config.MapperConfig;
import bohdan.markatov.org.dto.comment.CommentResponseDto;
import bohdan.markatov.org.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CommentMapper {

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "taskId", source = "task.id")
    CommentResponseDto toDto(Comment comment);
}
