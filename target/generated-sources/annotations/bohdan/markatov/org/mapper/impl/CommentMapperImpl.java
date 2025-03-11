package bohdan.markatov.org.mapper.impl;

import bohdan.markatov.org.dto.comment.CommentResponseDto;
import bohdan.markatov.org.mapper.CommentMapper;
import bohdan.markatov.org.mapper.UserMapper;
import bohdan.markatov.org.model.Comment;
import bohdan.markatov.org.model.Task;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-11T22:07:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    private final UserMapper userMapper;

    @Autowired
    public CommentMapperImpl(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    @Override
    public CommentResponseDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDto commentResponseDto = new CommentResponseDto();

        Long id = commentTaskId( comment );
        if ( id != null ) {
            commentResponseDto.setTaskId( id );
        }
        if ( comment.getId() != null ) {
            commentResponseDto.setId( comment.getId() );
        }
        if ( comment.getText() != null ) {
            commentResponseDto.setText( comment.getText() );
        }
        if ( comment.getAuthor() != null ) {
            commentResponseDto.setAuthor( userMapper.toDto( comment.getAuthor() ) );
        }
        if ( comment.getPublicationDate() != null ) {
            commentResponseDto.setPublicationDate( comment.getPublicationDate() );
        }
        commentResponseDto.setChanged( comment.isChanged() );

        return commentResponseDto;
    }

    private Long commentTaskId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Task task = comment.getTask();
        if ( task == null ) {
            return null;
        }
        Long id = task.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
