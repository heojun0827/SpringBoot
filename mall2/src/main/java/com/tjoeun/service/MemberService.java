package com.tjoeun.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tjoeun.entity.Member;
import com.tjoeun.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		// 가입하려는 회원과 같은 회원이 있는지 검증하는 메소드 호출하기
		validateDuplicate(member);
		Member savedMember = memberRepository.save(member);
		return savedMember;
	}
	
  // 가입하려는 회원과 같은 회원이 있는지 검증하는 메소드
	private void validateDuplicate(Member member) {
		Member foundMember = memberRepository.findByEmail(member.getEmail());
		if (foundMember != null) {
			throw new IllegalStateException("이미 가입하신 회원입니다");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// email 로 회원 조회해서 로그인하기
		Member member = memberRepository.findByEmail(email);
		// Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);  
		
		if(member == null) {
			throw new UsernameNotFoundException("해당 이메일로 가입한 회원이 없습니다 : " + email);
		}
		/*
		if(!member.isPresent()) {
			throw new UsernameNotFoundException("해당 이메일로 가입한 회원이 없습니다 : " + email);
		}
		*/
		
		log.info(">>>>>>>>>>>> loadUserByUsername : " + member);
		
		return User.builder()
				       .username(member.getEmail())
				       .password(member.getPassword())
				       .roles(member.getRole().toString())
				       .build();
			
	}
	

}



