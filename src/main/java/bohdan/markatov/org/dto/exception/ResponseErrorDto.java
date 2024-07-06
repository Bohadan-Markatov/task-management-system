package bohdan.markatov.org.dto.exception;

import java.util.List;
import lombok.Data;

@Data
public class ResponseErrorDto {
    private String time;
    private String status;
    private List<String> errors;
}
