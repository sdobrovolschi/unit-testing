package com.example.cart.domain.model;

import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;

public record CartId(UUID value) {

    public static CartId newCartId() {
        return new CartId(randomUUID());
    }
}
