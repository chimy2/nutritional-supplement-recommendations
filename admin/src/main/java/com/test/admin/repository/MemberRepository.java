package com.test.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.admin.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
