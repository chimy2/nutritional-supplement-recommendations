package com.test.admin.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.admin.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

	private final AdminService service;
	

	@GetMapping("/exists/id/{id}")
	public ResponseEntity<Void> checkAdminIdExists(@PathVariable("id") String id) {
		
		boolean isExisted = service.existsById(id);
		return isExisted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/exists/email/{email}")
	public ResponseEntity<Void> checkAdminEmailExists(@PathVariable("email") String email) {
		
		boolean isExisted = service.existsByEmail(email);
		return isExisted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
}
