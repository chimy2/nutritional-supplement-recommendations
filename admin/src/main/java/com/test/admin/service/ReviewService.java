package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dao.ReviewQueryRepository;
import com.test.admin.dto.ReviewDTO;
import com.test.admin.entity.Review;
import com.test.admin.repository.ReviewRepository;

@Service
public class ReviewService extends BoardServiceImpl<Review, ReviewDTO>{
	

	private final ReviewRepository repository;
	
	private final ReviewQueryRepository queryRepository;

	public ReviewService(ReviewRepository repository, ReviewQueryRepository queryRepository) {
		super(repository);
		this.repository = repository;
		this.queryRepository = queryRepository;
	}
	
	public List<ReviewDTO> getList() {
		return repository.findAll().stream().map(Review::toDTO).toList();
	}
}
