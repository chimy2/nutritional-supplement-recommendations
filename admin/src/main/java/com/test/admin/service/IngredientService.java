package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.dto.IngredientDTO;
import com.test.admin.entity.Ingredient;
import com.test.admin.repository.IngredientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {

	private final IngredientRepository repository;
	
	public List<IngredientDTO> getDTOList() {
		return repository.findAllByOrderByName().stream().map(Ingredient::toDTO).toList();
	}
	
	public IngredientDTO get(Long seq) {
		return repository.findById(seq).orElse(null).toDTO();
	}
	
	public List<IngredientDTO> getList(List<Long> ingredientSeqs) {
		return repository.findAllBySeqIn(ingredientSeqs).stream().map(Ingredient::toDTO).toList();
	}
}
