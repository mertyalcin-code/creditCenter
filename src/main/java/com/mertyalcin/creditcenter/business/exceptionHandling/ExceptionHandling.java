package com.mertyalcin.creditcenter.business.exceptionHandling;

import com.mertyalcin.creditcenter.core.utils.messages.Messages;
import com.mertyalcin.creditcenter.core.utils.results.ErrorResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@RestControllerAdvice
@Log4j2
public class ExceptionHandling implements ErrorController {

    // For Validation Exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleValidationException(MethodArgumentNotValidException argumentNotValidException){

        String message = argumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        log.warn("Validation error"+ argumentNotValidException.getBindingResult().getFieldErrors());
        return new ErrorResult(message);
    }

    // For Deleting data which have relation in database
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResult handHttpMessageExceptionError(DataIntegrityViolationException dataIntegrityViolationException){
        log.warn("dataIntegrityViolationException"+dataIntegrityViolationException.getMessage());
        return new ErrorResult(Messages.DATA_INTEGRITY_VIOLATION);
    }
}
