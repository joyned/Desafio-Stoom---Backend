package com.challenge.stoom.core;

import com.challenge.stoom.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class StoomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ServiceResponse> handleEmptyRequiredFields(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.append(fieldError.getDefaultMessage() + " ");
        }
        return ResponseBuilder.responseFail(errors.toString(), HttpStatus.BAD_REQUEST);
    }


}
