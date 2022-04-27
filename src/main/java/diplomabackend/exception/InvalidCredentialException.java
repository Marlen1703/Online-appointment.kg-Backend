package diplomabackend.exception;

import diplomabackend.exception.common.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialException extends BaseRuntimeException {

    public InvalidCredentialException() {
        super("Not valid credentials",HttpStatus.FORBIDDEN);
    }
}
