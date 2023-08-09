package com.tjoeun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing : 감시기능
@Configuration
@EnableJpaAuditing
public class AuditConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}

}
