package com.test.admin.service;

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
	
	public NutriService(ProductInfoRepository repository, ProductInfoQueryRepository queryRepository) {
		super(repository);
		this.repository = repository;
		this.queryRepository = queryRepository;
	}
	
	
}
