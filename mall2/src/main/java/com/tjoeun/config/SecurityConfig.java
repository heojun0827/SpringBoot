package com.tjoeun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// @Bean : Spring Framework 가 메모리에 미리 객체를 생성해놓음
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		/*
		http.formLogin()
		.loginPage("/members/login")
		.defaultSuccessUrl("/")
		.usernameParameter("email")
		.failureUrl("/members/login/error")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
		.logoutSuccessUrl("/");
		*/
		return http.build();
	}
}
