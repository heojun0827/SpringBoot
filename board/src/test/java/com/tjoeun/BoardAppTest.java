package com.tjoeun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tjoeun.entity.Answer;
import com.tjoeun.entity.Question;
import com.tjoeun.repository.AnswerRepository;
import com.tjoeun.repository.QuestionRepository;

@SpringBootTest
class BoardAppTest {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	@DisplayName("질문 테스트1")
	void questionTest() {
		Question q1 = new Question();
		q1.setSubject("Spring Boot ???");
		q1.setContent("Spring Boot 에 대해 알고 싶습니다");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("Querydsl ???");
		q2.setContent("Querydsl 에 대해 알고 싶습니다");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);
	}
	
	@Test
	@DisplayName("조회 테스트1")
	void selectTest1() {
		List<Question> questionList = questionRepository.findAll();
		assertEquals(2, questionList.size());
		
		Question q1 = questionList.get(0);
		assertEquals("Spring Boot ???", q1.getSubject());
	}
	
	@Test
	@DisplayName("조회 테스트2")
	void selectTest2() {
		
		Optional<Question> questionOne = questionRepository.findById((long)2);
		
		if(questionOne.isPresent()) {
			Question q1 = questionOne.get();
			
			assertEquals("Querydsl ???", q1.getSubject());
		}
	}
	
	@Test
	@DisplayName("조회 테스트3")
	void selectTest3() {
		Question q1 = questionRepository.findBySubjectAndContent("Spring Boot ???", "Spring Boot 에 대해 알고 싶습니다");
		assertEquals(1, q1.getId());
	}
	
	   @Test
	   @DisplayName("조회테스트4")
	   void selectTest4() {
	      //like문법
	      List<Question> questionList = questionRepository.findBySubjectLike("Spr%%");
	      
	      Question q1 = questionList.get(0);
	      assertEquals(1, q1.getId());
	   }
	   
	   @Test
	   @DisplayName("수정테스트1")
	   void updateTest1() {
		   Optional<Question> q1 = questionRepository.findById((long)1);
		   
		   
		   assertTrue(q1.isPresent());
		   
		  Question question = q1.get();
		  question.setSubject("Spring Board ???");
		  questionRepository.save(question);
			    
	   }
	   @Test
	   @DisplayName("삭제테스트1")
	   void deleteTest1() {
		   
		   assertEquals(2, questionRepository.count());
		   
		   Optional<Question> q1 = questionRepository.findById((long)1);
		   assertTrue(q1.isPresent());
		   Question question = q1.get();
		   questionRepository.delete(question);
		   
		   assertEquals(1, questionRepository.count());
	   }
	   
	   @Test
	   @DisplayName("질문테스트1")
	   void answerTest1() {
		   //DB 에서 2번 질문글 가져오기
		  Question q1 = questionRepository.findById((long)2)
										  .orElseThrow(EntityNotFoundException::new);
		   		
		   // assertTrue(q1.isPresent()); Optional 자체일때만 사용
		   // Question question = q1.get();
		   
		   Answer answer1 = new Answer();
		   answer1.setContent("Querydsl은 정적 타입을 사용해서 SQL 과 같은 Query 를 생성할 수 있도록 해주는 Open Source Framework 입니다");
		   // 어떤 질문글에 대한 답변글인지 표시함
		   answer1.setQuestion(q1);
		   answer1.setCreateDate(LocalDateTime.now());
		   answerRepository.save(answer1);
	   }
	   /*
	    현재 답글이 어느 질문글에 대한 답글한지 테스트하기
	    */
	   
	   @Test
	   @DisplayName("답변글 조회테스트(select)1")
	   void questionAnserTest1() {
		   
		 Answer answer1 = answerRepository.findById((long)1)
		   				   			      .orElseThrow(EntityNotFoundException::new);  
		 
		 assertEquals(2, answer1.getQuestion().getId());
	   }
	   
	   @Test
	   @DisplayName("질문글에대한 답변글 조회 테스트-1")
	   void questionAnswerTest1() {
	      Question q1 = questionRepository.findById((long)2)
	                              .orElseThrow();
	      
	      Answer answer1 = answerRepository.findById((long)1)
	                               .orElseThrow(EntityNotFoundException::new);
	      String answer = "Querydsl는 정적 타입을 사용하여 SQL과 같은 Query를 생성할 수 있도록 해주는 Open Source Framework입니다";
	      assertEquals(answer,answer1.getContent());
	      // answer1 답변글에 있는 질문글의 answer1 Entity의 question_id가 q1 Entity의 question_id와 같은가
	      assertEquals(answer1.getQuestion().getId(),q1.getId());
	   }
}
