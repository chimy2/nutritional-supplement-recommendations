package com.test.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.admin.dto.NoticeDTO;
import com.test.admin.entity.Notice;
import com.test.admin.repository.NoticeQueryDSLRepository;
import com.test.admin.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeRepository noticeRepository;
	
	private final NoticeQueryDSLRepository noticeQueryDSLRepository;

	public int getCount() {
		return (int) noticeRepository.count();
	}

	public List<NoticeDTO> getNoticeList(int offset, int limit) {
		return noticeQueryDSLRepository.findAllPagenation(offset, limit)
				.stream().map(notice -> notice.toDTO()).toList();
	}

	public Optional<Notice> get(Long seq) {
		return noticeRepository.findById(seq);
	}

	public Notice insert(Notice notice) {
		return noticeRepository.save(notice);
	}

	public Notice update(Notice notice) {
		return noticeRepository.save(notice);
	}

}
