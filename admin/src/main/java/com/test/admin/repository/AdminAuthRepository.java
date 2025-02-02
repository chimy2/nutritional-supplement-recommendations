package com.test.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.admin.auth.AdminRole;
import com.test.admin.entity.AdminAuth;

@Repository
public interface AdminAuthRepository extends JpaRepository<AdminAuth, Long> {

	AdminAuth findByRole(AdminRole role);

}
