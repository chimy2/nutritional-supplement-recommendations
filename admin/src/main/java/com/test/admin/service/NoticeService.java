package com.test.admin.service;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.admin.auth.AdminDetails;
import com.test.admin.auth.AdminRole;
import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dao.NoticeQueryRepository;
import com.test.admin.dto.AdminDTO;
import com.test.admin.dto.NoticeDTO;
import com.test.admin.entity.Admin;
import com.test.admin.entity.AdminAuth;
import com.test.admin.entity.Notice;
import com.test.admin.repository.NoticeRepository;

@Service
public class NoticeService extends BoardServiceImpl<Notice, NoticeDTO> {

	private final NoticeRepository repository;
	private final NoticeQueryRepository queryRepository;
	private final AdminService adminService;
	
	public NoticeService(NoticeRepository repository, NoticeQueryRepository queryRepository, AdminService adminService) {
		super(repository);
		this.repository = repository;
		this.queryRepository = queryRepository;
		this.adminService = adminService;
	}
	
	public long count() {
		return repository.count();
	}
	
	@Override
	public NoticeDTO create(NoticeDTO dto) {

		dto.setRegDate(LocalDateTime.now());
		
		return save(dto);
	}
	
	@Override
	public NoticeDTO update(NoticeDTO dto) {
		return save(dto);
	}

	public void deleteAdminNoticeList(Long adminSeq) {
		Admin admin = Admin.builder()
				.seq(adminSeq)
				.build();
		
		repository.deleteByAdmin(admin);
	}
	
	public NoticeDTO save(NoticeDTO dto) {
		
	    dto.setAdmin(adminService.getCurrentAdmin());
		
		Function<AdminRole, AdminAuth> authResolver = adminService.getAuthResolver();

		Notice entity = repository.save(dto.toEntity(authResolver));

		return entity.toDTO();
	}
	
}
