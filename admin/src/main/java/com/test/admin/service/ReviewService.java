package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.dao.ReviewQueryRepository;
import com.test.admin.dto.ReviewDTO;
import com.test.admin.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepository repository;
	
	private final ReviewQueryRepository queryRepository;

	public int getCount() {
		return (int) repository.count();
	}

	public List<ReviewDTO> getReviewList(int offset, int limit) {
		return queryRepository.findAllPagenation(offset, limit)
				.stream().map(review -> review.toDTO()).toList();
	}

	public ReviewDTO get(Long seq) {
		return repository.findById(seq).get().toDTO();
	}

}
