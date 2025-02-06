package com.test.admin.dto;

import java.time.LocalDateTime;
import java.util.function.Function;

import com.test.admin.auth.AdminRole;
import com.test.admin.board.BoardDTO;
import com.test.admin.entity.AdminAuth;
import com.test.admin.entity.Notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO extends BoardDTO<Notice> {

	private Long seq;
	
	private String title;
	
	private String content;
	
	private LocalDateTime regDate;
	
	private AdminDTO admin;

	public Notice toEntity(Function<AdminRole, AdminAuth> authResolver) {
		
		return Notice.builder()
				.seq(this.seq)
				.title(this.title)
				.content(this.content)
				.regDate(this.regDate)
				.admin(this.admin.toEntity(authResolver))
				.build();
	}
	
	@Override
	public Notice toEntity() {
		throw new UnsupportedOperationException("Use toEntity(Function<AdminRole, AdminAuth>) instead.");
	}
	
}
