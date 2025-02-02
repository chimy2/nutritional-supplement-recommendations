package com.test.admin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.test.admin.entity.AdminAuth;
import com.test.admin.service.AdminAuthService;

@Component
public class StringToAdminAuthConverter implements Converter<String, AdminAuth>{

	@Autowired
	private AdminAuthService service;

	@Override
	@Nullable
	public AdminAuth convert(@NonNull String source) {
		
		System.out.println("StringToAdminAuthConverter" + source);
		
        return service.findByRole(source);
	}
}
