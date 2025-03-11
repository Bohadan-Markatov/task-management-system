package bohdan.markatov.org.exception.handler;

import bohdan.markatov.org.dto.exception.ResponseErrorDto;
import bohdan.markatov.org.exception.EntityNotFoundException;
import bohdan.markatov.org.exception.RegistrationException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request
    ) {
        ResponseErrorDto error = getResponseErrorDto(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST, getErrors(ex));
        return new ResponseEntity<>(error, headers, status);
    }

    @ExceptionHandler({
            RegistrationException.class,
            RuntimeException.class
    })
    public ResponseEntity<Object> handleCustomBadRequestException(RuntimeException ex) {
        ResponseErrorDto errorDto = getResponseErrorDto(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST, List.of(ex.getMessage()));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<Object> handleCustomNotFoundException(RuntimeException ex) {
        ResponseErrorDto errorDto = getResponseErrorDto(
                LocalDateTime.now(), HttpStatus.NOT_FOUND, List.of(ex.getMessage()));
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    private ResponseErrorDto getResponseErrorDto(LocalDateTime dateTime,
                                                 HttpStatus status, List<String> errors) {
        ResponseErrorDto errorDto = new ResponseErrorDto();
        errorDto.setTime(dateTime.toString());
        errorDto.setStatus(status.toString());
        errorDto.setErrors(errors);
        return errorDto;
    }

    private List<String> getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            return error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
