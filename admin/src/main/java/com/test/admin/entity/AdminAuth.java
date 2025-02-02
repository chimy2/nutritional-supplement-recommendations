package com.test.admin.entity;

import com.test.admin.auth.AdminRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "adminAuth")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private AdminRole role;
    
	public AdminAuth(AdminRole role) {
		this.role = role;
	}
}
