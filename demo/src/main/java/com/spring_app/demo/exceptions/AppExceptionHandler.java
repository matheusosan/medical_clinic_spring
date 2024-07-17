package com.spring_app.demo.exceptions;

import com.spring_app.demo.exceptions.ClientExceptions.ClientAlreadyExistsException;
import com.spring_app.demo.exceptions.ClientExceptions.ClientNotFoundException;
import com.spring_app.demo.exceptions.ScheduleExceptions.OutOfWorkingPeriodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    private ResponseEntity<String> clientNotFoundHandler(ClientNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    private ResponseEntity<String> clientAlreadyExistsHandler(ClientAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(OutOfWorkingPeriodException.class)
    private ResponseEntity<String> outOfWorkingPeriodHandler(OutOfWorkingPeriodException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }


}
