package com.sametp.github_actions.exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Objects;
@Component
public final class ExceptionMapper {
    public Boolean checkExceptionMessage(Exception exception){
        return  Objects.isNull(exception.getMessage()) || exception.getMessage().isBlank();
    }
    public ErrorResponse responseForBlankOrNull(){
        return new ErrorResponse();
    }
    public ErrorResponse fromException(Exception exception){
        if (checkExceptionMessage(exception)) return responseForBlankOrNull();
        return new ErrorResponse(exception.getMessage());
    }
    public ErrorResponse fromException(MethodArgumentNotValidException exception){
        if (checkExceptionMessage(exception)) return responseForBlankOrNull();
        HashMap<String,String> errors = new HashMap<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(it -> {
                    String fieldName = ((FieldError) it).getField();
                    String errorMessage = it.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        return new ErrorResponse("Fields Not Valid",errors);
    }

}
