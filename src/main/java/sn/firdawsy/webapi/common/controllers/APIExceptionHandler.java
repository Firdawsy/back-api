package sn.firdawsy.webapi.common.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sn.firdawsy.webapi.common.exceptions.AbstractException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nureynisow on 06/04/2018.
 * for firdawsy
 */
@Slf4j
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity simpleExceptionHandler(Exception e){
        Map<String, Object> errorObject = new HashMap<>();
        errorObject.put("dateTime", new Date().toString());
        errorObject.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.put("code", "UNKNOWN_ERROR");
        if(e.getMessage()!=null) errorObject.put("message", e.getMessage());

        log.error("[EXCEPTION] abstract exception handling : {}", e);
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity abstractExceptionHandler(AbstractException e){
        Map<String, Object> errorObject = new HashMap<>();
        errorObject.put("dateTime", new Date().toString());
        errorObject.put("status", e.getStatus());
        errorObject.put("code", e.getErrorCode());
        if(e.getMessage()!=null) errorObject.put("message", e.getMessage());

        log.error("[EXCEPTION][{}] abstract exception handling : {}", e.getClassName(), e);
        return new ResponseEntity<>(errorObject, e.getStatus());
    }


}
