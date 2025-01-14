package com.test.admin.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.test.admin.board.BoardServiceImpl;
import com.test.admin.dao.NoticeQueryRepository;
import com.test.admin.dto.NoticeDTO;
import com.test.admin.entity.Notice;
import com.test.admin.repository.NoticeRepository;

@Service
public class NoticeService extends BoardServiceImpl<Notice, NoticeDTO> {

	private final NoticeRepository repository;
	private final NoticeQueryRepository queryRepository;
	
	public NoticeService(NoticeRepository repository, NoticeQueryRepository queryRepository) {
		super(repository);
		this.repository = repository;
		this.queryRepository = queryRepository;
	}
	
	public long count() {
		return repository.count();
	}
	
	@Override
	public NoticeDTO create(NoticeDTO dto) {
		
		dto.setRegDate(LocalDateTime.now());
		
		return super.create(dto);
	}
	
}
