package com.example.cart.use.cases;

import com.example.cart.domain.model.Cart;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.util.UUID;

import static com.example.cart.domain.model.CartId.newCartId;
import static java.util.Collections.emptySet;

@RequiredArgsConstructor
class CreateEmptyCartService {

    private final CartRepository cartRepository;
    private final Clock clock;

    UUID createEmptyCart() {
        var cart = new Cart(newCartId(), emptySet(), clock.instant());
        cartRepository.add(cart);
        return cart.getId().value();
    }
}
