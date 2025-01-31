package com.test.admin.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.admin.auth.AdminDetails;
import com.test.admin.board.BoardRepository;
import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dto.AdminDTO;
import com.test.admin.entity.Admin;
import com.test.admin.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
public class AdminService extends BoardServiceImpl<Admin, AdminDTO> implements UserDetailsService {
	

	private final AdminRepository repository;

	public AdminService(AdminRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin = repository.findById(username);

		if (admin != null) {
			return new AdminDetails(admin);
		}
		
		return null;
	}

}
