package com.test.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.admin.board.BoardController;
import com.test.admin.dto.NoticeDTO;
import com.test.admin.service.NoticeService;

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
		
	    dto.setAdmin(service.getCurrentAdmin());
	    
	    return super.post(model, dto);
	}
	
	@Override
	@PutMapping("/{seq}")
	public String put(Model model, Long seq, NoticeDTO dto) {

	    dto.setAdmin(service.getCurrentAdmin());
		
		return super.put(model, seq, dto);
	}
	
}
