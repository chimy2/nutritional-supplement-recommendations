package com.test.admin.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.test.admin.entity.AdminAuth;
import com.test.admin.service.AdminAuthService;

@Component
public class StringToAdminAuthSetConverter implements Converter<String, Set<AdminAuth>>{

	@Autowired
	private AdminAuthService service;

	@Override
	@Nullable
	public Set<AdminAuth> convert(@NonNull String source) {
        String[] roles = source.split(",");

        return Arrays.stream(roles)
                .map(service::findByRole)
                .collect(Collectors.toSet());	
	}
}
