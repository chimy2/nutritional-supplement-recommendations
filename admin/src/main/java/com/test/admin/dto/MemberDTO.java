package com.test.admin.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.test.admin.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	private Long seq;
	
//	아이디
	private String username;
	
//	이메일
	private String email;
	
//	비밀번호
	private String password;
	
//	이름
	private String name;
	
//	닉네임
	private String nickname;
	
//	생년월일
	private LocalDate dob;
	
//	성별
	private Character gender;

//	전화번호
	private String telephone;
	
//	주소
	private String address;
	
//	계정상태(0: 탈퇴, 1: 활성화)
	private int status;
	
//	가입일
	private LocalDateTime createTime;
	
//	수정시각
	private LocalDateTime updateTime; 

    public Member toEntity() {
        return Member.builder()
                .seq(this.seq)
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .nickname(this.nickname)
                .dob(this.dob)
                .gender(this.gender)
                .telephone(this.telephone)
                .address(this.address)
                .status(this.status)
                .createTime(this.createTime)
                .updateTime(this.updateTime)
                .build();
    }
}
