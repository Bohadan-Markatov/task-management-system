package bohdan.markatov.org.mapper.impl;

import bohdan.markatov.org.dto.task.CreateTaskRequestDto;
import bohdan.markatov.org.dto.task.TaskResponseDto;
import bohdan.markatov.org.mapper.ProjectMapper;
import bohdan.markatov.org.mapper.TaskMapper;
import bohdan.markatov.org.mapper.UserMapper;
import bohdan.markatov.org.model.Task;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-31T12:52:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;

    @Autowired
    public TaskMapperImpl(ProjectMapper projectMapper, UserMapper userMapper) {

        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Task toModel(CreateTaskRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        if ( dto.getStatus() != null ) {
            task.setStatus( dto.getStatus() );
        }
        else {
            task.setStatus( Task.Status.NOT_STARTED );
        }
        if ( dto.getPriority() != null ) {
            task.setPriority( dto.getPriority() );
        }
        else {
            task.setPriority( Task.Priority.MEDIUM );
        }
        if ( dto.getName() != null ) {
            task.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            task.setDescription( dto.getDescription() );
        }
        if ( dto.getDeadline() != null ) {
            task.setDeadline( dto.getDeadline() );
        }

        return task;
    }

    @Override
    public TaskResponseDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        if ( task.getId() != null ) {
            taskResponseDto.setId( task.getId() );
        }
        if ( task.getName() != null ) {
            taskResponseDto.setName( task.getName() );
        }
        if ( task.getDescription() != null ) {
            taskResponseDto.setDescription( task.getDescription() );
        }
        if ( task.getStatus() != null ) {
            taskResponseDto.setStatus( task.getStatus() );
        }
        if ( task.getPriority() != null ) {
            taskResponseDto.setPriority( task.getPriority() );
        }
        if ( task.getProject() != null ) {
            taskResponseDto.setProject( projectMapper.toDto( task.getProject() ) );
        }
        if ( task.getResponsibleUser() != null ) {
            taskResponseDto.setResponsibleUser( userMapper.toDto( task.getResponsibleUser() ) );
        }
        if ( task.getDeadline() != null ) {
            taskResponseDto.setDeadline( task.getDeadline() );
        }

        return taskResponseDto;
    }
}
