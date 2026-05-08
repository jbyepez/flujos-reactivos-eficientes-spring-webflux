package com.john.springwebflux.business.port.out.dto;

public record Id(
        Integer id,
        Character type,
        String number
) {
}
