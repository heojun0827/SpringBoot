package com.tjoeun.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tjoeun.entity.Answer;
import com.tjoeun.entity.Comment;
import com.tjoeun.entity.Question;
import com.tjoeun.entity.Users;
import com.tjoeun.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	public Comment create(Question question, Users users, Answer answer, String content) {
	
		Comment c = new Comment();
		c.setContent(content);
		c.setCreateDate(LocalDateTime.now());
		c.setQuestion(question);
		c.setUsers(users);
		c = commentRepository.save(c);
		
		return c;
	}
	
	public Optional<Comment> getComment(Long id){
		return commentRepository.findById(id);
	}
	
	public Comment modify(Comment c, String content) {
		c.setContent(content);
		c.setModifyDate(LocalDateTime.now());
		c = commentRepository.save(c);
		return c;
	}
	
	public void delete(Comment c) {
		commentRepository.delete(c);
	}
	
	
}




