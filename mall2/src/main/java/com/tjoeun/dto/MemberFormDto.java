package com.tjoeun.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberFormDto {
	
	// 회원가입할 때 입력해야 되는 내용을
	// MemberFormDto 클래스의 멤버변수로 지정함
	// name, email, password, address
	
	private String name;
	private String email;
	private String password;
	private String address;
}
