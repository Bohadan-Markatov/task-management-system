package bohdan.markatov.org.dto.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotNull(message = "Text cannot be null")
    @NotEmpty(message = "Text cannot be empty")
    @Size(max = 500, message = "Text cannot exceed 500 characters")
    private String text;
}

