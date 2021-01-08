package com.gpfarms.fruitstand.sales.catalog;

import com.gpfarms.fruitstand.sales.catalog.category.Uri;
import com.gpfarms.fruitstand.sales.catalog.product.Products;

public interface FindProductsFromCategory {

	Products byUri(Uri categoryUri);
}
