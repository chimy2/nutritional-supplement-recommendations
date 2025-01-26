package com.test.admin.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.admin.dto.IngredientDTO;
import com.test.admin.entity.Ingredient;

@SpringBootTest
class IngredientServiceTest {
	
	@Autowired
	private IngredientService service;

	@Test
	void testGetDTOList() {
		
		List<IngredientDTO> list = service.getDTOList();
		
		System.out.println(list);
		
		assertNotNull(list);
	}

}
