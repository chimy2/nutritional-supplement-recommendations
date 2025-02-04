package com.test.admin.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	private final AdminAuthService adminAuthService;
	
	private final BCryptPasswordEncoder encoder;

	public AdminService(AdminRepository repository, AdminAuthService adminAuthService, BCryptPasswordEncoder encoder) {
		super(repository);
		this.repository = repository;
		this.adminAuthService = adminAuthService;
		this.encoder = encoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin = repository.findById(username);

		if (admin != null) {
			return new AdminDetails(admin);
		}
		
		return null;
	}

	public boolean existsById(String id) {
		return repository.existsById(id);
	}

	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public AdminDTO create(AdminDTO dto) {
		
		dto.setPw(encoder.encode(dto.getPw()));

		return save(dto);
	}
	
	@Override
	public AdminDTO update(AdminDTO dto) {

		return save(dto);
	}
	
	private AdminDTO save(AdminDTO dto) {
		
	    Admin admin = dto.toEntity(adminAuthService::findByRole);
	    return repository.save(admin).toDTO();
	}
}
