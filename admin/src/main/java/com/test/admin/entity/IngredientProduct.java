package com.test.admin.entity;

import com.test.admin.dto.IngredientDTO;

import jakarta.persistence.Column;
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
@Table(name = "ingredientProduct")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name = "product_seq")
	private Long productSeq;

	@Column(name = "ingredient_seq")
	private Long ingredientSeq;

}
