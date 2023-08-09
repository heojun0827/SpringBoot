package com.tjoeun.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dto.AnswerFormDto;
import com.tjoeun.dto.QuestionFormDto;
import com.tjoeun.entity.Question;
import com.tjoeun.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	
	@GetMapping("/list")
	public String list(Model model) {
		// 질문글 전체 조회하기
		List<Question> questionList = questionService.findAll();
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model, AnswerFormDto answerFormDto) {
		Question question = questionService.getQuestionOne(id);
		model.addAttribute("question", question);
		return "question_detail";
	}

	@GetMapping("/create")
	public String createQuestion(QuestionFormDto questionFormDto) {
		return "question_form";
	}
	
	@PostMapping("/create")
	public String createQuestion(@Valid QuestionFormDto questionFormDto,
			                         BindingResult result) {
		if(result.hasErrors()) {
			return "question_form";
		}
		
		// 질문 DB 에 저장하기 - 서비스의 메소드 호출
		questionService.saveQuestion(questionFormDto.getSubject(), questionFormDto.getContent());
		
		return "redirect:/question/list";
		
	}
	
	
}




