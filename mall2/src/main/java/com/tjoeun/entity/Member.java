package com.tjoeun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.tjoeun.constant.Role;
import com.tjoeun.dto.MemberFormDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter 
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Long id;
  
	private String name;
	
	@Column(unique=true)
	private String email;
	private String password;
	private String address;
	
  // Enum 을 멤버변수로 사용할 수 있도록 해 주는 annotation
	@Enumerated(EnumType.STRING)
	private Role role;
	
	// SecutiryConfig 클래스에서 
	// 회원가입할 때 입력한 비밀번호 암호 처리한 것을
	// DB 에 적용하기
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
	
		// 비밀번호 암호처리하기
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		// 암호화된 비밀번호를 Member Entity 의 password 에 저장함
		member.setPassword(password);
		member.setRole(Role.USER);
		
		return member;
	}
	
	
}
