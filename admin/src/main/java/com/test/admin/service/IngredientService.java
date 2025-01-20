package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.entity.Ingredient;
import com.test.admin.repository.IngredientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {

	private final IngredientRepository repository;
	
	public List<Ingredient> getList() {
		return repository.findAllByOrderByName();
	}
}
