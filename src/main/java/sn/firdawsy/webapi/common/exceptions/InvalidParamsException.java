package sn.firdawsy.webapi.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by nureynisow on 14/04/2018.
 * for firdawsy
 */
public class InvalidParamsException extends AbstractException {
    public InvalidParamsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getErrorCode() {
        return "INVALID_PARAMS";
    }
}
