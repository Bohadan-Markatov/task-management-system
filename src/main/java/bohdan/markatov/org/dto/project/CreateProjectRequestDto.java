package bohdan.markatov.org.dto.project;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProjectRequestDto {
    @NotNull(message = "Project name cannot be null.")
    @NotEmpty(message = "Project name cannot be empty.")
    @Size(min = 1, max = 100, message = "Project name must be between 1 and 100 characters.")
    private String name;
    @NotNull(message = "Project description cannot be null.")
    @NotEmpty(message = "Project description cannot be empty.")
    @Size(min = 1, max = 500, message = "Project description must be between 1 and 500 characters.")
    private String description;
}
