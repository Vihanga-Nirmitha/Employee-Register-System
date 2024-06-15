package lk.quontacom.task.ers.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ERSException extends  Exception{
    private final HttpStatus statusCode;
    private final String errorMessage;

    public ERSException(HttpStatus statusCode,
                        String errorMessage,
                        String logMessage) {
        super(logMessage);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

}
