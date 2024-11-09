package com.example.cart.domain.model;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public record ItemId(UUID value) {

    public static ItemId newItemId() {
        return new ItemId(randomUUID());
    }
}
