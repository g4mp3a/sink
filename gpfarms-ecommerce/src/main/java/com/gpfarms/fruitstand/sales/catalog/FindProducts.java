package com.gpfarms.fruitstand.sales.catalog;

import com.gpfarms.fruitstand.sales.cart.item.ProductId;
import com.gpfarms.fruitstand.sales.catalog.product.Product;
import com.gpfarms.fruitstand.sales.catalog.product.Products;

public interface FindProducts {

	Products all();

	Product byId(ProductId id);
}
