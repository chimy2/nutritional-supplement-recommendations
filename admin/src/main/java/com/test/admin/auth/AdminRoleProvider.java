package com.test.admin.auth;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component("AdminRoleProvider")
public class AdminRoleProvider {

	private final Map<String, AdminRole> roles;

	public AdminRoleProvider() {
		this.roles = Arrays.stream(AdminRole.values()).collect(Collectors.toMap(this::convertKey, Function.identity()));
	}

	private String convertKey(AdminRole role) {
		String[] names = role.name().split("_");
		
		return Arrays.stream(names, 1, names.length)
	        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
	        .collect(Collectors.joining(""))
	        .replaceFirst("^.", Character.toLowerCase(names[1].charAt(0)) + "");
	}
	
	public AdminRole get(String role) {
		return roles.get(role);
	}
	
	public boolean hasRole(Set<AdminRole> roles, String feature) {
		return roles.stream().anyMatch(role -> {
			return role.toString().contains(feature);
		});
	}
}
