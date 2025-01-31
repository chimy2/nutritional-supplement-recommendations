package com.test.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.admin.board.BoardController;
import com.test.admin.dto.AdminDTO;
import com.test.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BoardController<AdminDTO> {
	
	private final AdminService service;

	public AdminController(AdminService service) {
		super(service, "admin");
		this.service = service;
	}

}
