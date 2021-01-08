package com.gpfarms.fruitstand.sales.catalog.product;

import com.gpfarms.fruitstand.common.primitives.Money;
import com.gpfarms.fruitstand.sales.catalog.category.CategoryId;

public interface Product {

	ProductId id();

	Title title();

	Description description();

	Money price();

	void changeTitle(Title title);

	void changeDescription(Description description);

	void changePrice(Money price);

	void putForSale();

	void categorize(CategoryId categoryId);
}
