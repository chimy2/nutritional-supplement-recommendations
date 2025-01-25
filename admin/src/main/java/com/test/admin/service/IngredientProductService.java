package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.dto.ProductInfoDTO;
import com.test.admin.entity.IngredientProduct;
import com.test.admin.repository.IngredientProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientProductService {

	private final IngredientProductRepository repository;
	
	public void create(Long productSeq, List<Long> ingredientSeqs) {
		if(ingredientSeqs == null) return;
		
		for(Long ingredientSeq : ingredientSeqs) {
			IngredientProduct relation = IngredientProduct.builder()
					.productSeq(productSeq)
					.ingredientSeq(ingredientSeq)
					.build();
			
			repository.save(relation);
		}
	}
}
