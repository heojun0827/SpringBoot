package com.tjoeun.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dto.AnswerFormDto;
import com.tjoeun.entity.Question;
import com.tjoeun.service.AnswerService;
import com.tjoeun.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
	
	private final QuestionService questionService;
	
	private final AnswerService answerService;
	
	// id <-- Question 의 id
	@PostMapping("/create/{id}")
	public String createAnswer(@Valid AnswerFormDto answerFormDto, Model model,
                             BindingResult result, @PathVariable("id") Long id) {
		
		Question question = questionService.getQuestionOne(id);
		
		if(result.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		
		// 불러온 question entity 에 대한 답변(answer) 저장하기
		answerService.createAnswer(question, answerFormDto.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}

}
