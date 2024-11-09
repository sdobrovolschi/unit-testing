package com.example.cart.use.cases;

import com.example.cart.domain.model.CartId;
import com.example.cart.domain.model.ItemId;
import com.example.cart.domain.model.Quantity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static io.vavr.API.Left;

@RequiredArgsConstructor
class IncreaseItemQuantityService {

    private final CartRepository cartRepository;

    //String for demo purpose
    Either<String, Void> increaseItemQuantity(UUID cartId, UUID itemId, int quantity) {
        return cartRepository.find(new CartId(cartId))
                .map(cart -> cart.increaseQuantity(new ItemId(itemId), new Quantity(quantity))
                        .map(r -> {
                            cartRepository.add(cart);
                            return r;
                        }))
                .orElse(Left("Cart not found"));
    }
}
