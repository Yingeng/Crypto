package com.example.CryptoCurrency.controller.Exception;

public class InvalidStartAndEndTime extends RuntimeException {
    public InvalidStartAndEndTime() {
        super();
    }

    public InvalidStartAndEndTime(String message) {
        super(message);
    }


    public InvalidStartAndEndTime(String message, Throwable cause) {
        super(message, cause);
    }
}
