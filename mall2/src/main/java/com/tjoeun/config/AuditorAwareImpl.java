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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String userId = "";
		
	  // 인증 정보를 성공적으로 가져온 후, userId 를 얻어옴
		if (authentication != null) {
			userId = authentication.getName();
			log.info(">>>>>>>>>>>> 회원 : " + userId);
		}
		
	  // NullPointerException 을 방지하기 위해서 Optional 을 사용함
    // 이렇게 하면 userId 가 있는 경우에는 userId 를 가져가고
    // 없다고 해도 Optional 객체가 반환되므로 NullPointerException 이 발생하지 않음
    // 이 부분에서 Optional 을 사용하지 않고 if 문으로 처리해도 되나, code 가 장황해짐
		return Optional.of(userId);
	}

}



