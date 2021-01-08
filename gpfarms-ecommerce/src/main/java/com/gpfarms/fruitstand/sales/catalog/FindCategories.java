package com.gpfarms.fruitstand.sales.catalog;

import com.gpfarms.fruitstand.sales.catalog.category.Categories;
import com.gpfarms.fruitstand.sales.catalog.category.Category;
import com.gpfarms.fruitstand.sales.catalog.category.CategoryId;

public interface FindCategories {

	Categories all();

	Category byId(CategoryId id);
}
