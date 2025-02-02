package com.test.admin.service;

import org.springframework.stereotype.Service;

import com.test.admin.auth.AdminRole;
import com.test.admin.entity.AdminAuth;
import com.test.admin.repository.AdminAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

	private final AdminAuthRepository repository;
	
	public AdminAuth findByRole(String role) {
		System.out.println("public AdminAuth findByRole(String role) {");
		return findByRole(AdminRole.valueOf(role));
	}
	
	public AdminAuth findByRole(AdminRole role) {
		System.out.println("public AdminAuth findByRole(AdminRole role) {");
		return repository.findByRole(role);
	}
}
