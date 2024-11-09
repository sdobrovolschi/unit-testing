package com.example.cart.use.cases;

import com.example.cart.domain.model.Cart;
import com.example.cart.domain.model.CartId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.Clock;
import java.time.Instant;

import static java.time.ZoneId.systemDefault;
import static java.util.Collections.emptySet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@MockitoSettings
class CreateEmptyCartServiceTest {

    @Mock
    CartRepository cartRepository;
    Clock clock;
    CreateEmptyCartService service;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), systemDefault());
        service = new CreateEmptyCartService(cartRepository, clock);
    }

    @Test
    void emptyCartCreation() {
        var cartId = service.createEmptyCart();

        verify(cartRepository).add(Mockito.refEq(new Cart(new CartId(cartId), emptySet(), clock.instant())));
        verifyNoMoreInteractions(cartRepository);
    }
}
