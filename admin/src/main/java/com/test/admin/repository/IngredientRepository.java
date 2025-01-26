package com.test.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.admin.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

	List<Ingredient> findAllByOrderByName();

	List<Ingredient> findAllBySeqIn(List<Long> ingredientSeqs);

}
