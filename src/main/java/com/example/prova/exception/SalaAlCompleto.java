package com.example.prova.exception;

public class SalaAlCompleto extends RuntimeException{
    public SalaAlCompleto() {
        super("Non ci sono più posti disponibili");
    }

    public SalaAlCompleto(String message) {
        super(message);
    }
}
