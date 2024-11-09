package com.example.cart.use.cases;

import com.example.cart.domain.model.Cart;
import com.example.cart.domain.model.CartId;
import com.example.cart.domain.model.Item;
import com.example.cart.domain.model.Quantity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static com.example.cart.domain.model.CartId.newCartId;
import static com.example.cart.domain.model.ItemId.newItemId;
import static com.example.cart.test.data.CartMother.randomCart;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.*;

@MockitoSettings
class IncreaseItemQuantityServiceTest {

    @Mock
    CartRepository cartRepository;
    @InjectMocks
    IncreaseItemQuantityService service;
    @Captor
    ArgumentCaptor<Cart> cartArgumentCaptor;

    @Test
    void itemQuantityIncrement() {
        var cartId = newCartId();
        var itemId = newItemId();
        var cart = randomCart()
                .with(item -> item.with(itemId).with(new Quantity(2)))
                .build();

        when(cartRepository.find(cartId)).thenReturn(Optional.of(cart));

        var result = service.increaseItemQuantity(cartId.value(), itemId.value(), 1);

        //first output
        assertThat(result).isRight();

        //second output
        verify(cartRepository).add(cartArgumentCaptor.capture());
        assertThat(cartArgumentCaptor.getValue().getItems())
                .extracting(Item::getQuantity)
                .containsExactly(new Quantity(3));
        verifyNoMoreInteractions(cartRepository);
    }

    @Test
    void unknownCartHandling() {
        var cartId = newCartId();
        var itemId = newItemId();

        when(cartRepository.find(cartId)).thenReturn(Optional.empty());

        var result = service.increaseItemQuantity(cartId.value(), itemId.value(), 1);

        assertThat(result).containsOnLeft("Cart not found");
        verifyNoMoreInteractions(cartRepository);
    }

    @Test
    void unknownItemHandling() {
        var cartId = newCartId();
        var itemId = newItemId();
        var cart = randomCart().build();

        when(cartRepository.find(cartId)).thenReturn(Optional.of(cart));

        var result = service.increaseItemQuantity(cartId.value(), itemId.value(), 1);

        assertThat(result).containsOnLeft("Item not found");
        verifyNoMoreInteractions(cartRepository);
    }
}
