package com.example.cart.test.data;

import com.example.cart.domain.model.Item;
import com.example.cart.domain.model.ItemId;
import com.example.cart.domain.model.Product;
import com.example.cart.domain.model.Quantity;
import org.javamoney.moneta.Money;

import static com.example.cart.domain.model.ItemId.newItemId;
import static com.example.cart.domain.model.ProductId.newProductId;
import static java.math.BigDecimal.TEN;

public class ItemMother {

    public static Builder randomItem() {
        return new Builder();
    }

    public static class Builder {

        private ItemId itemId = newItemId();
        private Product product = new Product(newProductId(), Money.of(TEN, "EUR"));
        private Quantity quantity = new Quantity(5);

        public Builder with(ItemId itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder with(Quantity quantity) {
            this.quantity = quantity;
            return this;
        }

        public Item build() {
            return new Item(itemId, product, quantity);
        }
    }
}
