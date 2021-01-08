package com.gpfarms.fruitstand.sales.catalog.category;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public final class Title {

	private final @NonNull String title;

	public Title(@NonNull String title) {
		var titleVal = title.strip();
		if (titleVal.isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}
		this.title = titleVal;
	}

	public String value() {
		return title;
	}
}
