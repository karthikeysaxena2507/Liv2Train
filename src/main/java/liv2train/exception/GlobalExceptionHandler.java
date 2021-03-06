package liv2train.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to handle all the exceptions across all controllers
 * within a single class
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method to handle validation failures
     * @param ex the MethodArgumentNotValidException object
     * @param headers the HttpHeaders associated with the request
     * @param status the HttpStatus of the request
     * @param request The request object
     * @return A customized error response, specifying the exact cause of error
     */
    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErrorResponse> errors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Validation Exception",
                    error.getField(),
                    error.getDefaultMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY.value()
            );
            errors.add(errorResponse);
        }
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method to handle ant internal server error
     * @param exc the exception caused from the server
     * @return A customized error response, specifying the exact cause of error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException exc) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCause("Unknown Error occurred with server: {}");
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
