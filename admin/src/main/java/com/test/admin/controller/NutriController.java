package com.test.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.admin.board.BoardController;
import com.test.admin.dto.ProductInfoDTO;
import com.test.admin.service.IngredientService;
import com.test.admin.service.NutriService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/nutri") 
public class NutriController extends BoardController<ProductInfoDTO> {
	
	private final NutriService service;
	
	@Autowired
	private IngredientService ingredientService;

	public NutriController(NutriService service) {
		super(service, "nutri");
        this.service = service;
	}
	
	@Override
	public String getWriteView(Model model) {
		
		model.addAttribute("ingredients", ingredientService.getDTOList());
		
		return super.getWriteView(model);
	}
	
	@Override
	public String getEditView(Model model, Long seq) {

		model.addAttribute("ingredients", ingredientService.getDTOList());
		
		return super.getEditView(model, seq);
	}
}
