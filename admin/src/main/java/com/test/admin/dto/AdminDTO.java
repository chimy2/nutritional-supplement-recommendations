package com.test.admin.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.test.admin.board.BoardDTO;
import com.test.admin.entity.Admin;
import com.test.admin.entity.AdminAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO extends BoardDTO<Admin> {

	private Long seq;
	private String id;
	private String pw;
	private String name;
	private LocalDate birthDate;
	private String email;
	private Set<AdminAuth> auths;
	
	public AdminDTO(String id, String pw, String name, String birthDate, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
		this.email = email;
	}

	public Admin toEntity() {
		return Admin.builder()
				.seq(this.seq)
				.id(this.id)
				.pw(this.pw)
				.name(this.name)
				.birthDate(this.birthDate)
				.email(this.email)
				.auths(this.auths)
				.build();
	}
}
