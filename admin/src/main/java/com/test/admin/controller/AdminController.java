package com.test.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.admin.board.BoardController;
import com.test.admin.dto.AdminDTO;
import com.test.admin.service.AdminService;
import com.test.admin.service.NoticeService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/admin")
public class AdminController extends BoardController<AdminDTO> {

	private final AdminService service;

	private final NoticeService noticeService;

	public AdminController(AdminService service, NoticeService noticeService) {
		super(service, "admin");
		this.service = service;
		this.noticeService = noticeService;
	}

	@Override
	@DeleteMapping("/{seq}")
	@Transactional
	public String delete(Model model, @PathVariable("seq") Long seq) {
		
		noticeService.deleteAdminNoticeList(seq);

		return super.delete(model, seq);
	}

}
