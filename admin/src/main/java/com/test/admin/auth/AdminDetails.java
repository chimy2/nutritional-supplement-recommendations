package com.test.admin.auth;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.admin.dto.AdminDTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AdminDetails implements UserDetails {
	
	private final Long seq;
	
	private final String username;
	
	private final String password;
	
	private final String name;
	
	private final Set<AdminRole> auths;
	
	public AdminDetails(AdminDTO dto) {
		this.seq = dto.getSeq();
		this.username = dto.getId();
		this.password = dto.getPw();
		this.name = dto.getName();
		this.auths = dto.getAuths();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

        return auths.stream()
        		.map(auth -> new SimpleGrantedAuthority(auth.toString()))
                .collect(Collectors.toList());
	}
	
}
