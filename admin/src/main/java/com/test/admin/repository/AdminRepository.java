package com.test.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.admin.board.BoardRepository;
import com.test.admin.entity.Admin;

public interface AdminRepository extends BoardRepository<Admin>{

	Admin findById(String id);

	List<Admin> findBySeqGreaterThanEqual(int seq);

	boolean existsById(String id);

	boolean existsByEmail(String email);

	long count();

}
