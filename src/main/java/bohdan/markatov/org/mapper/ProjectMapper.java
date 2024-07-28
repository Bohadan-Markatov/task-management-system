package bohdan.markatov.org.mapper;

import bohdan.markatov.org.config.MapperConfig;
import bohdan.markatov.org.dto.project.CreateProjectRequestDto;
import bohdan.markatov.org.dto.project.ProjectResponseDto;
import bohdan.markatov.org.model.Project;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = UserMapper.class)
public interface ProjectMapper {

    ProjectResponseDto toDto(Project project);

    Project toModel(CreateProjectRequestDto dto);
}
