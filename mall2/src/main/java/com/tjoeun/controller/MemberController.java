package com.tjoeun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dto.MemberFormDto;
import com.tjoeun.entity.Member;
import com.tjoeun.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// localhost:8787/member/new
	@GetMapping("/new")
	public String memberForm(MemberFormDto memberFormDto) {
		return "member/memberForm";
	}
	/*
	@GetMapping("/new")
	public String memberForm2(Model model) {
	  model.addAttribute("memberFormDto", new MemberFormDto());	  
		return "member/memberForm";
	}
	
	@GetMapping("/new")
	public String memberForm3(MemberFormDto memberFormDto, Model model) {
	  model.addAttribute("memberFormDto", memberFormDto);	  
		return "member/memberForm";
	}
	*/
	
	@PostMapping("/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, 
			                     BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "member/memberForm";
		}
		
		try {
  		Member member = Member.createMember(memberFormDto, passwordEncoder);
  		memberService.saveMember(member);
		}catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		
		log.info(">>>>>>>>>>>> 회원가입 완료 : " + memberFormDto);
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "member/memberLoginForm";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMessage", "이메일 또는 비밀번호를 확인해 주세요.")
;		return "member/memberLoginForm";
	}
	

}





