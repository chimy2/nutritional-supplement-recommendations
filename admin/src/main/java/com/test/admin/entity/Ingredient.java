package com.test.admin.entity;

import com.test.admin.dto.IngredientDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "ingredient")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String name;
	private boolean category;
	
	public IngredientDTO toDTO() {
		return IngredientDTO.builder()
				.seq(this.seq)
				.name(this.name)
				.category(this.category)
				.build();
	}
}
