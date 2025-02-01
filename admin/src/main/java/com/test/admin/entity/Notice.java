package com.test.admin.entity;

import java.time.LocalDateTime;

import com.test.admin.board.Board;
import com.test.admin.dto.NoticeDTO;

import jakarta.persistence.CascadeType;
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
@Table(name = "noticePost")
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String title;
	
	private String content;
	
	private LocalDateTime regDate;

	@ManyToOne
	@JoinColumn(name = "admin_seq")
	private Admin admin;
	
	@Override
	public NoticeDTO toDTO() {
		NoticeDTO dto = NoticeDTO.builder()
				.seq(this.seq)
				.title(this.title)
				.content(this.content)
				.regDate(this.regDate)
				.build();
		
		if(this.admin != null) {
			dto.setAdmin(this.admin.toDTO());
		}
		
		return dto;
	}
}
