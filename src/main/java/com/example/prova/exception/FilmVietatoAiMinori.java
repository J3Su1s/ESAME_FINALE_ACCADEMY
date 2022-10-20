package com.example.prova.exception;

public class FilmVietatoAiMinori extends RuntimeException {

    public FilmVietatoAiMinori() {
        super("Questo film è vietato ai minori di 18 anni");
    }

    public FilmVietatoAiMinori(String message) {
        super(message);
    }
}
