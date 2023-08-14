package com.tjoeun.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.tjoeun.dto.AnswerFormDto;
import com.tjoeun.dto.QuestionFormDto;
import com.tjoeun.entity.Question;
import com.tjoeun.entity.Users;
import com.tjoeun.service.QuestionService;
import com.tjoeun.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	private final UsersService usersService;
	
	
	// 검색을 위해서 
	// @RequestParam(value="keyword", defaultValue="") String keyword <-- 추가함
	@GetMapping("/list")
	public String list(Model model, 
			               @RequestParam(value="page", defaultValue="0") int page,
	                   @RequestParam(value="keyword", defaultValue="") String keyword) {
		
		Page<Question> paging = questionService.getList(page, keyword);
		
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model, AnswerFormDto answerFormDto) {
		Question question = questionService.getQuestionOne(id);
		model.addAttribute("question", question);
		return "question_detail";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String createQuestion(QuestionFormDto questionFormDto) {
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String createQuestion(@Valid QuestionFormDto questionFormDto,
			                         BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "question_form";
		}
		Users users = usersService.getUsers(principal.getName());
		
		// 질문 DB 에 저장하기 - 서비스의 메소드 호출
		questionService.saveQuestion(questionFormDto.getSubject(), questionFormDto.getContent(), users);
		
		return "redirect:/question/list";
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionFormDto questionFormDto, 
			                         @PathVariable("id") Long id, Principal principal) {
		
		Question question = questionService.getQuestionOne(id);
		if(!question.getUsers().getUsername().equals(principal.getName())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
		}
		questionFormDto.setSubject(question.getSubject());
		questionFormDto.setContent(question.getContent());
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionFormDto questionFormDto, BindingResult result,
			                         Principal principal,  @PathVariable("id") Long id) {
		
		if(result.hasErrors()) {
			return "question_form";
		}
		
		Question question = questionService.getQuestionOne(id);
		if(!question.getUsers().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
		}
		
	  questionService.modify(question, questionFormDto.getSubject(), questionFormDto.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Long id) {
		
		Question question = questionService.getQuestionOne(id);
		if(!question.getUsers().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한 없음");
		}
		
	  questionService.delete(question);
		
		return "redirect:/";
	}
	
	// 추천 저장
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal, @PathVariable("id") Long id) {
		
		Question question = questionService.getQuestionOne(id);
		Users users = usersService.getUsers(principal.getName());
		questionService.vote(question, users);
		
		return String.format("redirect:/question/detail/%s", id);
	}

	
	
}




