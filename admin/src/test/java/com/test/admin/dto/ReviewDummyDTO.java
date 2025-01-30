package com.test.admin.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDummyDTO {

	private Long memberSeq;
	
	private String title;
	
	private String category;
	
	private String name;
	
	private String content;
	
	private LocalDateTime regDate;

	public ReviewDummyDTO(int memberSeq, String title, String category, String name, String content, String regDate) {
		this.memberSeq = (long) memberSeq;
		this.title = title;
		this.category = category;
		this.name = name;
		this.content = content;
		this.regDate = LocalDateTime.parse(regDate, DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss"));
	}
	
	public ReviewDTO toRealDTO() {
		return ReviewDTO.builder()
				.member(MemberDTO.builder().seq(this.memberSeq).build())
				.category(this.category)
				.name(this.name)
				.title(this.title)
				.content(this.content)
				.regDate(this.regDate)
				.build();
	}
}
