package com.john.springwebflux.service.dto;

import java.time.LocalDate;

public record Birthdate(
        Integer documentId,
        LocalDate birthDate
) {
}
