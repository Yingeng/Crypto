package com.example.CryptoCurrency.controller.Exception;

public class UnsupportedFrequency extends RuntimeException {
    public UnsupportedFrequency() {
        super();
    }

    public UnsupportedFrequency(String message) {
        super(message);
    }


    public UnsupportedFrequency(String message, Throwable cause) {
        super(message, cause);
    }
}
