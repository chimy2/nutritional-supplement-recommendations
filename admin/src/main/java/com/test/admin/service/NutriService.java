package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dao.ProductInfoQueryRepository;
import com.test.admin.dto.IngredientDTO;
import com.test.admin.dto.ProductInfoDTO;
import com.test.admin.entity.ProductInfo;
import com.test.admin.repository.ProductInfoRepository;

@Service
public class NutriService extends BoardServiceImpl<ProductInfo, ProductInfoDTO> {

	private final ProductInfoRepository repository;
	private final ProductInfoQueryRepository queryRepository;
	private final IngredientService ingredientService;
	
	public NutriService(ProductInfoRepository repository, ProductInfoQueryRepository queryRepository, IngredientService ingredientService) {
		super(repository);
		this.repository = repository;
		this.queryRepository = queryRepository;
		this.ingredientService = ingredientService;
	}
	
	@Override
	public ProductInfoDTO create(ProductInfoDTO dto) {
		
		setIngredientsFromIds(dto);
		
		return super.create(dto);
	}
	
	@Override
	public ProductInfoDTO update(ProductInfoDTO dto) {
		
		setIngredientsFromIds(dto);
		
		return super.update(dto);
	}
	
	public void setIngredientsFromIds(ProductInfoDTO dto) {

		List<Long> ingredientSeqs = dto.getIngredientSeqs();
		
		List<IngredientDTO> ingreidients = ingredientService.getList(ingredientSeqs);
		
		dto.setIngredients(ingreidients);
		
	}
}
