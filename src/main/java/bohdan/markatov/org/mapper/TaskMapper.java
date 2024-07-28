package bohdan.markatov.org.mapper;

import bohdan.markatov.org.config.MapperConfig;
import bohdan.markatov.org.dto.task.CreateTaskRequestDto;
import bohdan.markatov.org.dto.task.TaskResponseDto;
import bohdan.markatov.org.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {ProjectMapper.class, UserMapper.class})
public interface TaskMapper {

    @Mapping(target = "status", source = "status",
            defaultExpression = "java(Task.Status.NOT_STARTED)")
    @Mapping(target = "priority", source = "priority",
            defaultExpression = "java(Task.Priority.MEDIUM)")
    Task toModel(CreateTaskRequestDto dto);

    TaskResponseDto toDto(Task task);
}
