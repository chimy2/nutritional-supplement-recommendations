package com.test.admin.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.test.admin.board.Board;
import com.test.admin.dto.AdminDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "admin")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String id;
	private String pw;
	private String name;
	private LocalDate birthDate;
	private String email;
	
	@ManyToMany
	@JoinTable(name = "adminAuthList", joinColumns = @JoinColumn(name = "admin_seq"), inverseJoinColumns = @JoinColumn(name = "adminAuth_seq"))
	private Set<AdminAuth> auths;
	
	@Override
	public AdminDTO toDTO() {
		return AdminDTO.builder()
				.seq(this.seq)
				.id(this.id)
				.pw(this.pw)
				.name(this.name)
				.birthDate(this.birthDate)
				.email(this.email)
				.auths(this.auths != null ? 
						this.auths.stream().map(auth -> auth.getRole()).collect(Collectors.toSet())
						: null)
				.build();
	}
}
