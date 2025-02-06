package com.test.admin.service;

import java.beans.Transient;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.admin.auth.AdminDetails;
import com.test.admin.auth.AdminRole;
import com.test.admin.board.BoardRepository;
import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dto.AdminDTO;
import com.test.admin.entity.Admin;
import com.test.admin.entity.AdminAuth;
import com.test.admin.repository.AdminRepository;

import jakarta.transaction.Transactional;
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
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin entity = repository.findById(username);

		return entity != null ? new AdminDetails(entity.toDTO()) : null;
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
		
	    Admin admin = dto.toEntity(getAuthResolver());
	    return repository.save(admin).toDTO();
	}

	public void updateAuths(Long seq, AdminDTO dto) {
		
		AdminDTO result = get(seq).get();
		
		result.setAuths(dto.getAuths());
		
		save(result);
	}
	
	public Function<AdminRole, AdminAuth> getAuthResolver() {
	    return adminAuthService::findByRole;
	}
	
	public AdminDTO getCurrentAdmin() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		AdminDetails principal = (AdminDetails) authentication.getPrincipal();

		AdminDTO dto = get(principal.getSeq()).orElse(null);

		return dto;
	}
}
