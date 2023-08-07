package com.tjoeun.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		
		// Auditing 기능은 인증 정보를 가져 온 후 사용함
		
		// 인증 정보 가져오기
		Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String userId = "";
		
		if(authentication != null) {
			userId = authentication.getName();
			log.info(">>>>>>>>>>>>> 회원 : " + userId);
		}
		
			
		//NullPointerException 을 방지하기 위해서 Optional 을 사용함
		return Optional.of(userId);
	}

}
