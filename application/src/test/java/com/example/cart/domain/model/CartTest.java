package com.example.cart.domain.model;

import org.junit.jupiter.api.Test;

import static com.example.cart.domain.model.ItemId.newItemId;
import static com.example.cart.test.data.CartMother.randomCart;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class CartTest {

    @Test
    void itemQuantityIncrement() {
        var itemId = newItemId();
        var cart = randomCart()
                .with(item -> item.with(itemId).with(new Quantity(3)))
                .build();

        var result = cart.increaseQuantity(itemId, new Quantity(2));

        //first output
        assertThat(result).isRight();

        //second output
        assertThat(cart.getItems())
                .extracting(Item::getQuantity)
                .containsExactly(new Quantity(5));
    }

    @Test
    void unknownItemHandling() {
        var itemId = newItemId();
        var cart = randomCart().build();

        var result = cart.increaseQuantity(itemId, new Quantity(1));

        assertThat(result).containsOnLeft("Item not found");
    }
}
