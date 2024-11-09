package com.example.cart.domain.model;

import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;

public record ProductId(UUID value) {

    public static ProductId newProductId() {
        return new ProductId(randomUUID());
    }
}
