package bohdan.markatov.org.mapper.impl;

import bohdan.markatov.org.dto.attachment.AttachmentResponseDto;
import bohdan.markatov.org.mapper.AttachmentMapper;
import bohdan.markatov.org.model.Attachment;
import bohdan.markatov.org.model.Task;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-11T22:07:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class AttachmentMapperImpl implements AttachmentMapper {

    @Override
    public AttachmentResponseDto toDto(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        AttachmentResponseDto attachmentResponseDto = new AttachmentResponseDto();

        Long id = attachmentTaskId( attachment );
        if ( id != null ) {
            attachmentResponseDto.setTaskId( id );
        }
        String name = attachmentTaskName( attachment );
        if ( name != null ) {
            attachmentResponseDto.setTaskName( name );
        }
        if ( attachment.getId() != null ) {
            attachmentResponseDto.setId( attachment.getId() );
        }
        if ( attachment.getFileName() != null ) {
            attachmentResponseDto.setFileName( attachment.getFileName() );
        }
        if ( attachment.getUploadDate() != null ) {
            attachmentResponseDto.setUploadDate( attachment.getUploadDate() );
        }

        return attachmentResponseDto;
    }

    private Long attachmentTaskId(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }
        Task task = attachment.getTask();
        if ( task == null ) {
            return null;
        }
        Long id = task.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String attachmentTaskName(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }
        Task task = attachment.getTask();
        if ( task == null ) {
            return null;
        }
        String name = task.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
