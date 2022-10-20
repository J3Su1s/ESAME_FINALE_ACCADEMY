package com.example.prova.utils;

import com.example.prova.data.enums.Genere;

import java.time.Instant;

public class Util {
    public static Long toLong(String value) {
        return value == null ? null : Long.parseLong(value);
    }

    public static Double toDouble(String value) {
        return value == null ? null : Double.parseDouble(value);
    }

    public static Instant toInstant(String value) {
        return value == null ? null : Instant.parse(value);
    }

    public static String fromInstant(Instant value) {
        return value == null ? null : value.toString();
    }

    public static String getGenere(Genere value) {
        return value == null ? null : value.name();
    }

    public static Genere getGenere(String value) {
        if (value == null) {
            return null;
        }
        for (Genere genere : Genere.values()) {
            if (genere.name().equals(value)) {
                return genere;
            }
        }
        return null;
    }

}
