package com.john.springwebflux.model;

public record Id(
        Integer id,
        Character type,
        String number
) {
}
