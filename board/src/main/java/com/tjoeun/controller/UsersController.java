package com.tjoeun.controller;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dto.UsersFormDto;
import com.tjoeun.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
	
	private final UsersService usersService;
	
	@GetMapping("/signup")
	public String signup(UsersFormDto usersFormDto) {
		log.info(">>>>>>>>>>>> 회원 가입 페이지로 이동");
		log.info(">>>>>>>>>>>> " + usersFormDto);
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UsersFormDto usersFormDto, BindingResult result) {
		// 비밀번호와 비밀번호확인 값이 일치하지 않은 경우
		if(!usersFormDto.getPassword1().equals(usersFormDto.getPassword2())) {
			result.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
			// return "signup_form";
		}
		
		if(result.hasErrors()) {
			return "signup_form";
		}
		
		// 회원 중복 검사하기
		// 회원정보가 제대로 입력된 경우
		
		try {
		  usersService.createUsers(usersFormDto.getUsername(), usersFormDto.getPassword1(), usersFormDto.getEmail());
		}catch(DataIntegrityViolationException e){
			e.printStackTrace();
			result.reject("signupFailed", "이미 가입한 회원입니다");
			return "signup_form";
		}catch(Exception e) {
			e.printStackTrace();
			result.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		log.info(">>>>>>>>>>>> 회원 가입 완료");
		log.info(">>>>>>>>>>>> " + usersFormDto);
		
		return "redirect:/";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
	
	
	
	
	
	
}
