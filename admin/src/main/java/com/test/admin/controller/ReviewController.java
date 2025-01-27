package com.test.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.admin.board.BoardController;
import com.test.admin.dto.ReviewDTO;
import com.test.admin.service.ReviewService;


@Controller
@RequestMapping("review")
public class ReviewController extends BoardController<ReviewDTO> {

	private ReviewService service;

	public ReviewController(ReviewService service) {
		super(service, "review");
		this.service = service;
	}
	
}
