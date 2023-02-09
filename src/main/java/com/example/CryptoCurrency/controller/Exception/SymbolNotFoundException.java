package com.example.CryptoCurrency.controller.Exception;

public class SymbolNotFoundException extends RuntimeException {
    public SymbolNotFoundException() {
        super();
    }

    public SymbolNotFoundException(String message) {
        super(message);
    }


    public SymbolNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
