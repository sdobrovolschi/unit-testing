package com.example.cart.domain.model;

import io.vavr.control.Either;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Set;

import static io.vavr.API.Left;
import static io.vavr.API.Right;

@RequiredArgsConstructor
@Getter
public final class Cart {

    private final CartId id;
    private final Set<Item> items;
    private final Instant createdAt;

    public Either<String, Void> increaseQuantity(ItemId itemId, Quantity quantity) {
        return items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .<Either<String, Void>>map(item -> {
                    item.increaseQuantity(quantity.value());
                    return Right(null);
                })
                .orElse(Left("Item not found"));
    }
}
