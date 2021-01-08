package com.gpfarms.fruitstand.sales.cart;

import com.gpfarms.fruitstand.sales.cart.item.ProductId;

public interface RemoveCartItem {

    void fromCart(CartId cartId, ProductId productId);
}
