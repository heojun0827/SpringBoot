package com.tjoeun.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="question_id")
	private Long id;
	
	@Column(length=200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL,
             orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Answer> answerList = new ArrayList<>();
	
	// 글쓴이
  @ManyToOne
  @JoinColumn(name="user_id")
	private Users users;
  
  // 수정날짜
  private LocalDateTime modifyDate;

  //추천
  @ManyToMany 
  Set<Users> voter;
  
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL,
           orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comment> commentList = new ArrayList<>();
}
