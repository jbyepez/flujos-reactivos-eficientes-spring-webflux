package com.john.springwebflux.service.dto;

public record Id(
        Integer id,
        Character type,
        String number
) {
}
