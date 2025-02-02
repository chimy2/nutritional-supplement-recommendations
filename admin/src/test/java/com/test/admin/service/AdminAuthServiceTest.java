package com.test.admin.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.admin.auth.AdminRole;
import com.test.admin.entity.AdminAuth;

@SpringBootTest
class AdminAuthServiceTest {
	
	@Autowired
	private AdminAuthService service;

	@Test
	void testFindByRole() {
		AdminRole role = AdminRole.ROLE_ADMIN_ALL;
		
		String request = role.toString();
		
		AdminAuth auth = service.findByRole(request);
		
		assertNotNull(auth);
	}

}
