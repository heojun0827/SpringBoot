package com.tjoeun.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.tjoeun.entity.Answer;
import com.tjoeun.entity.Question;
import com.tjoeun.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	// Question 객체와 문자열(content) 를 
	// 파라미터로 전달받아서 Entity 를 DB 에 저장함
  public void createAnswer(Question question, String content) {
  	
  	Answer answer = new Answer();
  	answer.setContent(content);
  	answer.setCreateDate(LocalDateTime.now());
  	answer.setQuestion(question);
  	
  	answerRepository.save(answer);
  	
  }
	
	
	

}
