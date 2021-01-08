package com.gpfarms.fruitstand.sales.catalog.category;

public interface Category {

	CategoryId id();

	Uri uri();

	Title title();

	void changeTitle(Title title);
}
