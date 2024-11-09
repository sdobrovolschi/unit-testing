package com.example.cart.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.money.MonetaryAmount;

@RequiredArgsConstructor
@Getter
public final class Product {

    private final ProductId id;
    private final MonetaryAmount price;

}
