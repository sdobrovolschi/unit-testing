package com.example.cart.use.cases;

import com.example.cart.domain.model.Cart;
import com.example.cart.domain.model.CartId;

import java.util.Optional;

public interface CartRepository {

    void add(Cart cart);

    Optional<Cart> find(CartId cartId);
}
