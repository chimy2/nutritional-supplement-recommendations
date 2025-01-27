package com.test.admin.dto;

import java.time.LocalDateTime;

import com.test.admin.board.BoardDTO;
import com.test.admin.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO extends BoardDTO<Review> {

	private Long seq;
	
	private String title;
	
	private String content;
	
	private LocalDateTime regDate;
	
	private MemberDTO member;

	@Override
	public Review toEntity() {
		return Review.builder()
				.seq(this.seq)
				.title(this.title)
				.content(this.content)
				.regDate(this.regDate)
				.member(this.member.toEntity())
				.build();
	}
	
}
