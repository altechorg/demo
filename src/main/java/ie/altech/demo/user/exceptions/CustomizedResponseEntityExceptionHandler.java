package ie.altech.demo.user.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * This exception handler will be applied to all project controllers
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleApiException(Exception ex, WebRequest request) {
        ExceptionResponseStructure responseStructure = new ExceptionResponseStructure(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponseStructure responseStructure = new ExceptionResponseStructure(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(responseStructure, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponseStructure responseStructure = new ExceptionResponseStructure(new Date(), "Validation failed", ex.getBindingResult().toString());
        return new ResponseEntity(responseStructure, HttpStatus.BAD_REQUEST);
    }
}
