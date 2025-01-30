package com.test.admin.entity;

import java.time.LocalDateTime;

import com.test.admin.board.Board;
import com.test.admin.board.BoardDTO;
import com.test.admin.dto.NoticeDTO;
import com.test.admin.dto.ReviewDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
public class Review extends Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String category;
	
	private String name;
	
	private String title;
	
	private String content;
	
	private LocalDateTime regDate;

	@ManyToOne
	@JoinColumn(name = "member_seq")
	private Member member;

	@Override
	public ReviewDTO toDTO() {
		return ReviewDTO.builder()
				.seq(this.seq)
				.category(this.category)
				.name(this.name)
				.title(this.title)
				.content(this.content)
				.regDate(this.regDate)
				.member(this.member.toDTO())
				.build();
	}
}
