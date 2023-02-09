package com.example.CryptoCurrency.controller;

import com.example.CryptoCurrency.controller.Exception.InvalidStartAndEndTime;
import com.example.CryptoCurrency.controller.Exception.SymbolNotFoundException;
import com.example.CryptoCurrency.controller.Exception.UnsupportedFrequency;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SymbolNotFoundException.class, UnsupportedFrequency.class, InvalidStartAndEndTime.class})
    public ResponseEntity<Object> handleInvalidArgument(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
