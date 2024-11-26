package com.test.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrfConfig) -> csrfConfig.disable())
		.headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())))
		.authorizeHttpRequests((authorizeRequests) -> 
            authorizeRequests
            .requestMatchers("/**").permitAll() // 정적 리소스 접근 허용
            .anyRequest().authenticated());
		
		//커스텀 로그인 설정
		http.formLogin(auth -> auth
						.loginPage("/login") //사용자 로그인 페이지 URL
						.defaultSuccessUrl("/") //로그인 성공시 페이지 URL 
						.loginProcessingUrl("/loginok").permitAll());

		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}