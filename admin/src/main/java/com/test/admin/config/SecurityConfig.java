package com.test.admin.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.admin.auth.AdminRole;
import com.test.admin.auth.CustomAccessDeniedHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
//    	URL 확인
    	
//    	http.addFilterBefore(new OncePerRequestFilter() {
//    	    @Override
//    	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//    	            throws ServletException, IOException {
//    	        System.out.println("Incoming request: " + request.getMethod() + " " + request.getRequestURI());
//    	        filterChain.doFilter(request, response);
//    	    }
//    	}, UsernamePasswordAuthenticationFilter.class);

        http.csrf((csrfConfig) -> csrfConfig.disable())
                .headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())))
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/css/**", "/js/**", "/template/**", "/img/**").permitAll()// 정적 리소스 접근 허용
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/", "/error").authenticated()                              
                                .requestMatchers("/board/**").denyAll()
                                
//                                notice 경로 시큐리티 설정  
                                .requestMatchers(HttpMethod.DELETE, "/notice/*").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_DELETE.toString())
                                
                                .requestMatchers(HttpMethod.PUT, "/notice/*").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_UPDATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/notice/write").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_CREATE.toString())
                                
                                .requestMatchers(HttpMethod.POST, "/notice").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_CREATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/notice/*/edit").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_UPDATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/notice/*").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_READ.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/notice").hasAnyAuthority(
                                		AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NOTICE_ALL.toString(), AdminRole.ROLE_NOTICE_READ.toString())
                                
//                              nutri 경로 시큐리티 설정  
                                .requestMatchers(HttpMethod.DELETE, "/nutri/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_DELETE.toString())
                                
                                .requestMatchers(HttpMethod.PUT, "/nutri/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_UPDATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/nutri/write").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_CREATE.toString())
                                
                                .requestMatchers(HttpMethod.POST, "/nutri").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_CREATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/nutri/*/edit").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_UPDATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/nutri/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_READ.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/nutri").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_NUTRI_ALL.toString(), AdminRole.ROLE_NUTRI_READ.toString())
                                
//                                review 경로 시큐리티 설정  
                                .requestMatchers(HttpMethod.DELETE, "/review/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_REVIEW_ALL.toString(), AdminRole.ROLE_REVIEW_DELETE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/review/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_REVIEW_ALL.toString(), AdminRole.ROLE_REVIEW_READ.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/review").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_REVIEW_ALL.toString(), AdminRole.ROLE_REVIEW_READ.toString())
                                
//                                admin 경로 시큐리티 설정  
                                .requestMatchers(HttpMethod.DELETE, "/admin/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_DELETE.toString())
                                
                                .requestMatchers(HttpMethod.PUT, "/admin/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_UPDATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/admin/write").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_CREATE.toString())
                                
                                .requestMatchers(HttpMethod.POST, "/admin").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_CREATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/admin/*/edit").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_UPDATE.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/admin/*").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_READ.toString())
                                
                                .requestMatchers(HttpMethod.GET, "/admin").hasAnyAuthority(
                                        AdminRole.ROLE_SUPER.toString(), AdminRole.ROLE_ADMIN_ALL.toString(), AdminRole.ROLE_ADMIN_READ.toString())
                                
                                .requestMatchers("/api/**").authenticated()
                                )
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
}
