package com.test.admin.repository;

import org.springframework.stereotype.Repository;

import com.test.admin.board.BoardRepository;
import com.test.admin.entity.ProductInfo;

@Repository
public interface ProductInfoRepository extends BoardRepository<ProductInfo> {
	
}
