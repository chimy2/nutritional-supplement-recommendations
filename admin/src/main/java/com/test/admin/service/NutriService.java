package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dao.ProductInfoQueryRepository;
import com.test.admin.dto.ProductInfoDTO;
import com.test.admin.entity.ProductInfo;
import com.test.admin.repository.ProductInfoRepository;

@Service
public class NutriService extends BoardServiceImpl<ProductInfo, ProductInfoDTO> {

	private final ProductInfoRepository repository;
	private final ProductInfoQueryRepository queryRepository;
	private final IngredientProductService ingredientProductService;
	
	public NutriService(ProductInfoRepository repository, ProductInfoQueryRepository queryRepository, IngredientProductService ingredientProductService) {
		super(repository);
		this.repository = repository;
		this.queryRepository = queryRepository;
		this.ingredientProductService = ingredientProductService;
	}
	
	@Override
	public ProductInfoDTO create(ProductInfoDTO dto) {
		List<Long> ingredientSeqs = dto.getIngredientSeqs();
		
		ProductInfoDTO result = super.create(dto);
		
		ingredientProductService.create(result.getSeq(), ingredientSeqs);
		
		return result;
	}
}
