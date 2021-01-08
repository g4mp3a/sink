package com.gpfarms.fruitstand.sales.cart;

import java.util.List;

import com.gpfarms.fruitstand.sales.cart.item.CartItem;
import com.gpfarms.fruitstand.sales.cart.item.ProductId;

public interface Cart {

    CartId id();

    List<CartItem> items();

    boolean hasItems();

    void add(CartItem toAdd);

    void remove(ProductId productId);

    void empty();
}

