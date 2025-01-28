package com.test.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.admin.dto.MemberDTO;
import com.test.admin.entity.Member;
import com.test.admin.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository repository;
	
	public MemberDTO create(MemberDTO dto) {
		return repository.save(dto.toEntity()).toDTO();
	}
	
	public MemberDTO create(Member entity) {
		return repository.save(entity).toDTO();
	}
	
	public List<MemberDTO> getList() {
		return repository.findAll().stream().map(Member::toDTO).toList();
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
}
