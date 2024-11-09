package com.example.cart.domain.model;

public record Quantity(int value) {

    public Quantity add(int amount) {
        return new Quantity(value + amount);
    }
}
