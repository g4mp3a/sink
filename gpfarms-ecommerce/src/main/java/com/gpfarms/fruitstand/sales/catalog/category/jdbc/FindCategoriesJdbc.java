package com.gpfarms.fruitstand.sales.catalog.category.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gpfarms.fruitstand.sales.catalog.FindCategories;
import com.gpfarms.fruitstand.sales.catalog.category.Categories;
import com.gpfarms.fruitstand.sales.catalog.category.Category;
import com.gpfarms.fruitstand.sales.catalog.category.CategoryId;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class FindCategoriesJdbc implements FindCategories {

	private final @NonNull JdbcTemplate jdbcTemplate;

	@Override
	public Categories all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category byId(CategoryId id) {
		// TODO Auto-generated method stub
		return null;
	}
}