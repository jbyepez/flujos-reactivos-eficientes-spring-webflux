package com.john.springwebflux.business.port.out.dto;

import java.time.LocalDate;

public record Birthdate(
        Integer documentId,
        LocalDate birthDate
) {
}
