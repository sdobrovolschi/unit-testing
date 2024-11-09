package com.example.cart.test.data;

import com.example.cart.domain.model.Cart;
import com.example.cart.domain.model.CartId;
import com.example.cart.domain.model.Item;

import java.time.Instant;
import java.util.Set;
import java.util.function.Consumer;

import static com.example.cart.domain.model.CartId.newCartId;
import static com.example.cart.test.data.ItemMother.randomItem;

public class CartMother {

    public static Builder randomCart() {
        return new Builder();
    }

    public static class Builder {

        private CartId cartId = newCartId();
        private Set<Item> items = Set.of(randomItem().build());
        private Instant createdAt = Instant.now();

        public Builder with(Consumer<ItemMother.Builder> block) {
            var item = randomItem();
            block.accept(item);
            items = Set.of(item.build());
            return this;
        }

        public Cart build() {
            return new Cart(cartId, items, createdAt);
        }
    }
}
