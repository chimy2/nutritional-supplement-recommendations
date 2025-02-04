package com.test.admin.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.test.admin.auth.AdminRole;

@Component
public class StringToAdminRoleSetConverter implements Converter<String, Set<AdminRole>>{

	@Override
	@Nullable
	public Set<AdminRole> convert(@NonNull String source) {
        String[] roles = source.split(",");

        return Arrays.stream(roles)
                .map(AdminRole::valueOf)
                .collect(Collectors.toSet());	
	}
}
