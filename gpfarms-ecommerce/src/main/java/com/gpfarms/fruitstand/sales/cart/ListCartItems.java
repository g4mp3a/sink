package com.gpfarms.fruitstand.sales.cart;

import java.util.List;

import com.gpfarms.fruitstand.sales.cart.item.CartItem;

public interface ListCartItems {
	
    List<CartItem> listCart(CartId cartId);
}