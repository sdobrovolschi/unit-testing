package com.example.cart.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Item {

    @EqualsAndHashCode.Include
    private final ItemId id;
    private final Product product;
    private Quantity quantity;

    public void increaseQuantity(int amount) {
        this.quantity = this.quantity.add(amount);
    }
}
