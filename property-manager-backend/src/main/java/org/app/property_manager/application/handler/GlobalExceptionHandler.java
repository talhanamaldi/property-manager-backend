package org.app.property_manager.application.handler;

import java.time.Instant;
import java.util.Map;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleBadCreds(BadCredentialsException ex) {
        return Map.of("timestamp", Instant.now(), "status", 401, "error", "Unauthorized", "message", "Invalid credentials");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalArg(IllegalArgumentException ex) {
        return Map.of("timestamp", Instant.now(), "status", 400, "error", "Bad Request", "message", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Map.of("timestamp", Instant.now(), "status", 400, "error", "Validation Failed", "message", msg);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ResponseBase> generalException(Exception ex) {
        ResponseBase error = new ResponseBase();
        error.errorMessage=ex.getMessage();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralWarning.class)
    public ResponseEntity<ResponseBase> generalWarning(Exception ex) {
        ResponseBase warning = new ResponseBase();
        warning.warningMessage=ex.getMessage();
        return new ResponseEntity<>(warning, HttpStatus.BAD_REQUEST);
    }
}
