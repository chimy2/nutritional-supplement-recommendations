package com.test.admin.converter;

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
public class StringSetToAdminAuthSetConverter implements Converter<Set<String>, Set<AdminAuth>>{

	@Autowired
	private AdminAuthService service;

	@Override
	@Nullable
	public Set<AdminAuth> convert(@NonNull Set<String> source) {
		
		System.out.println("StringSetToAdminAuthSetConverter" + source);
		
        return source.stream()
                .map(service::findByRole) // 변환 로직 유지
                .collect(Collectors.toSet());
	}
}
