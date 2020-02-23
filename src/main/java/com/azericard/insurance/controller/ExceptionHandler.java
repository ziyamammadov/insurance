package com.azericard.insurance.controller;

import com.azericard.insurance.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@ControllerAdvice
@RestController
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ExceptionEntity handleUserNotFoundException(UserNotFoundException ex) {
        return ExceptionEntity.builder()
                .code(104)
                .description("User not found")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CompanyNotFoundException.class)
    public ExceptionEntity handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return ExceptionEntity.builder()
                .code(104)
                .description("Company not found")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ExceptionEntity handleCompanyNotFoundException(ProductNotFoundException ex) {
        return ExceptionEntity.builder()
                .code(104)
                .description("Product not found")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(GeneralException.class)
    public ExceptionEntity handleGeneralException(GeneralException ex) {
        return ExceptionEntity.builder()
                .code(101)
                .description(ex.getMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessNotAllowedException.class)
    public ExceptionEntity handleAccessNotAllowedException(AccessNotAllowedException ex) {
        return ExceptionEntity.builder()
                .code(102)
                .description("You are not allowed to send request to this URL")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RollbackException.class)
    public ExceptionEntity handleUserNotFoundException(RollbackException ex) {
        return ExceptionEntity.builder()
                .code(106)
                .description(ex.getMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ExceptionEntity handleConstraintViolation(ConstraintViolationException ex) {
        StringJoiner sj = new StringJoiner(", ");
        ex.getConstraintViolations().forEach(cv -> sj.add(cv.getMessageTemplate()));
        return ExceptionEntity.builder()
                .code(105)
                .description(sj.toString())
                .build();
    }
}
