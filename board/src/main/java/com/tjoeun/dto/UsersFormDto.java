package com.tjoeun.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UsersFormDto {
	
	@NotEmpty(message="아이디는 반드시 입력해 주세요")
	@Size(min=4, max=20)
	private String username;
	
	@NotEmpty(message="비밀번호1은 반드시 입력해 주세요")
	private String password1;
	
	@NotEmpty(message="비밀번호2는 반드시 입력해 주세요")
	private String password2;
	
	@NotEmpty(message="이메일은 반드시 입력해 주세요")
	@Email
	private String email;
	
	
	
	
	
	
  
}



