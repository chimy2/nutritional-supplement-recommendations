package com.test.admin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.admin.auth.AdminDetails;
import com.test.admin.board.BoardController;
import com.test.admin.dto.NoticeDTO;
import com.test.admin.service.NoticeService;
import com.test.admin.util.PathHelper;

@Controller
@RequestMapping("/notice") 
public class NoticeController extends BoardController<NoticeDTO> {
	
	private final NoticeService service;

	public NoticeController(NoticeService service) {
		super(service, "notice");
        this.service = service;
	}

	
	@Override
	@PostMapping
	public String post(Model model, NoticeDTO dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		AdminDetails admin = (AdminDetails) authentication.getPrincipal();
		
	    dto.setAdminSeq(admin.getSeq());
	    
	    System.out.println("콘텐츠~");
	    System.out.println(dto.getContent());
	    System.out.println(dto.getContent().contains("\n") ? "있음" : "없음");
	    
	    return super.post(model, dto);
	}
	
}
