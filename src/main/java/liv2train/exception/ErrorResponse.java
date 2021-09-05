package liv2train.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class used for returning a customized error response
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String exceptionType;
    private String errorCausingField;
    private String errorCause;
    private int errorCode;

}
