package com.example.prova.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class AgeCalculator {
    public static int calculateAge(LocalDateTime birthDate, LocalDateTime currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate.toLocalDate(), currentDate.toLocalDate()).getYears();
        } else {
            return 0;
        }
    }
}
