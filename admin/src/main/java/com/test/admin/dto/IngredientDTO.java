package com.test.admin.dto;

import java.time.LocalDateTime;

import com.test.admin.entity.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

	private Long seq;
	private String name;
	private boolean category;

	public Ingredient toEntity() {
		return Ingredient.builder()
				.seq(this.seq)
				.name(this.name)
				.category(this.category)
				.build();
	}
}
