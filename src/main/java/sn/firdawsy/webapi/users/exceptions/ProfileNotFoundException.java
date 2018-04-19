package sn.firdawsy.webapi.users.exceptions;

import org.springframework.http.HttpStatus;
import sn.firdawsy.webapi.common.exceptions.AbstractException;

/**
 * Created by nureynisow on 14/04/2018.
 * for firdawsy
 */
public class ProfileNotFoundException extends AbstractException {
    public ProfileNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return "PROFILE_NOT_FOUND";
    }
}
