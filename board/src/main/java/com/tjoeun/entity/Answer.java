package com.tjoeun.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="answer_id")
	private Long id;
		
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	
	// 글쓴이
  @ManyToOne
  @JoinColumn(name="user_id")
	private Users users;
	
  // 수정날짜
  private LocalDateTime modifyDate;
  
  //추천
  @ManyToMany
  Set<Users> voter;
}
