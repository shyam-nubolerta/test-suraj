package org.userinputs.controller.advice;


import lombok.extern.slf4j.Slf4j;
import org.userinputs.exception.UpdateFailedException;
import org.userinputs.exception.UpdateRequestMalFormedException;
import org.userinputs.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class UserInputControllerAdvice {


    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(UpdateFailedException.class)
    public void handleConstraintViolationException(UpdateFailedException e) {
        ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(UpdateRequestMalFormedException.class)
    public void handleInvalidFileFormatException(UpdateRequestMalFormedException e) {
        ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public void handleException(UserNotFoundException e) {
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }


}
