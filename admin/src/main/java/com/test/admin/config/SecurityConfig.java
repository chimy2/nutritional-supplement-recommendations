package com.test.admin.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.admin.auth.AdminPermission;
import com.test.admin.auth.CustomAccessDeniedHandler;
import com.test.admin.auth.ResourceAdminRoleProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
        http
//        .csrf((csrfConfig) -> csrfConfig.disable())
	        .headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())))
	        .authorizeHttpRequests((authorizeRequests) -> {
	        	permitCommonRequests(authorizeRequests);
	        	permitSpecificRequests(authorizeRequests);
	        })
            .exceptionHandling(handling -> handling
                    .accessDeniedHandler(new CustomAccessDeniedHandler()))
            .addFilterBefore(new HiddenHttpMethodFilter(), UsernamePasswordAuthenticationFilter.class);
		
		//커스텀 로그인 설정
		http.formLogin(auth -> auth
						.loginPage("/login") //사용자 로그인 페이지 URL
						.defaultSuccessUrl("/") //로그인 성공시 페이지 URL 
						.loginProcessingUrl("/login")
				        .failureUrl("/login?error=true") // 로그인 실패 시 이동할 URL
						.permitAll());

		return http.build();
	}

	@Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.logout(auth -> auth
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );

        return http.build();
    }

    private void permitCommonRequests(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeRequests) {

        authorizeRequests
            .requestMatchers("/css/**", "/js/**", "/template/**", "/img/**").permitAll()// 정적 리소스 접근 허용
            .requestMatchers("/login").permitAll()
            .requestMatchers("/", "/error").authenticated()                              
            .requestMatchers("/board/**").denyAll()
            .requestMatchers("/api/**").authenticated();
	}
    
	private void permitSpecificRequests(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeRequests) {
    	String[] resources = { "notice", "nutri", "review", "admin" };

    	Arrays.stream(resources).forEach(res -> {
    		ResourceAdminRoleProvider provider = new ResourceAdminRoleProvider(res);

			if (provider.getRole(AdminPermission.Read) != null) {

	    		authorizeRequests
		    		.requestMatchers(HttpMethod.GET, provider.getPath()).hasAnyAuthority(provider.getRole(AdminPermission.Read))
	                .requestMatchers(HttpMethod.GET, provider.getSubPaths()).hasAnyAuthority(provider.getRole(AdminPermission.Read));
	    		
			} if (provider.getRole(AdminPermission.Create) != null) {

	    		authorizeRequests
	                .requestMatchers(HttpMethod.GET, provider.getWritePath()).hasAnyAuthority(provider.getRole(AdminPermission.Create))
	                .requestMatchers(HttpMethod.POST, provider.getPath()).hasAnyAuthority(provider.getRole(AdminPermission.Create));
	    		
			} if (provider.getRole(AdminPermission.Update) != null) {
				
				if(res.equals("admin")) {

		    		authorizeRequests
		    		.requestMatchers(HttpMethod.PUT, provider.getAdminAuthPath()).hasAnyAuthority(provider.getRole(AdminPermission.Update));
				} else {

					authorizeRequests
					.requestMatchers(HttpMethod.PUT, provider.getSubPaths()).hasAnyAuthority(provider.getRole(AdminPermission.Update));
				}
				
	    		authorizeRequests
	                .requestMatchers(HttpMethod.GET, provider.getEditPath()).hasAnyAuthority(provider.getRole(AdminPermission.Update));
	    		
			} if (provider.getRole(AdminPermission.Delete) != null) {

	    		authorizeRequests
        			.requestMatchers(HttpMethod.DELETE, provider.getSubPaths()).hasAnyAuthority(provider.getRole(AdminPermission.Delete));
	    		
			}
    	});
	}

//	URL 확인용
    private void checkRequestURL(HttpSecurity http) {
    	http.addFilterBefore(new OncePerRequestFilter() {
		    @Override
		    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		            throws ServletException, IOException {
		        System.out.println("Incoming request: " + request.getMethod() + " " + request.getRequestURI());
		        filterChain.doFilter(request, response);
		    }
		}, UsernamePasswordAuthenticationFilter.class);
    }
}
