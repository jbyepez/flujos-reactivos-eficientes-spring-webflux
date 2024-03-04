package com.john.springwebflux.model;

import java.time.LocalDate;

public record Birthdate(
        Integer documentId,
        LocalDate birthDate
) {
}
