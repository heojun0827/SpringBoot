package com.tjoeun.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.tjoeun.dto.CommentFormDto;
import com.tjoeun.entity.Answer;
import com.tjoeun.entity.Comment;
import com.tjoeun.entity.Users;
import com.tjoeun.service.AnswerService;
import com.tjoeun.service.CommentService;
import com.tjoeun.service.QuestionService;
import com.tjoeun.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentAnswerController {
   private final CommentService commentService;
   private final QuestionService questionService;
   private final UsersService usersService;
   private final AnswerService answerService;
   
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/create/answer/{id}")
   public String createComment(@PathVariable("id") Long id,CommentFormDto commentFormDTO) {
      return "comment_form";
   }
   
   @PreAuthorize("isAuthenticated()")
   @PostMapping("/create/answer/{id}")
   public String createComment(@PathVariable("id") Long id,@Valid CommentFormDto commentFormDTO,
                        BindingResult result,Principal principal,Model model) {
      Answer answer =  answerService.getAnswerOne(id);
      Users users = usersService.getUsers(principal.getName());
      
      if(result.hasErrors()) {         
         return "comment_form";
      }
      
      Comment c = commentService.create(answer, users, commentFormDTO.getContent());
      return String.format("redirect:/question/detail/%s",c.getAnswer().getQuestion().getId());
   }
   
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/modify/answer/{id}")
   public String modifyComment(@PathVariable("id") Long id,CommentFormDto commentFormDTO,Principal principal) {
//      Comment comment = commentService.getComment(id)
//                                      .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한 없음"));
      Optional<Comment> comment = commentService.getComment(id);
      //isPresent Optional이 null값이 아니고 Comment객체가 있다면 Comment객체를 꺼내오라는뜻
      if(comment.isPresent()) {
         Comment c = comment.get();
         if(!c.getUsers().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한 없음");
         }
         commentFormDTO.setContent(c.getContent());
      }   
      return "comment_form";
   }
   
   @PreAuthorize("isAuthenticated()")
   @PostMapping("/modify/answer/{id}")
   public String modifyComment(@Valid CommentFormDto commentFormDTO , BindingResult result,
                        Principal principal,@PathVariable("id") Long id) {
      if(result.hasErrors()) {
         return "comment_form";
      }
      
      Optional<Comment> comment = commentService.getComment(id);
      //isPresent Optional이 null값이 아니고 Comment객체가 있다면 Comment객체를 꺼내오라는뜻
      if(comment.isPresent()) {
         Comment c = comment.get();
         if(!c.getUsers().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한 없음");
         }
         //commentFormDTO.setContent(c.getContent());
         c = commentService.modify(c, commentFormDTO.getContent());
         return String.format("redirect:/question/detail/%s", c.getQuestionId());
      }else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"수정권한 없음");
//         return String.format("redirect:/question/detail/%s", c.getQuestionId());
      }
   }
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/delete/answer/{id}")
   public String deleteComment(@PathVariable("id") Long id,Principal principal) {
      Optional<Comment> comment = commentService.getComment(id);
      if(comment.isPresent()) {
         Comment c = comment.get();
         if(!c.getUsers().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제권한 없음");
         }
         commentService.delete(c);
         return String.format("redirect:/question/detail/%s", c.getQuestionId());
      }else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"삭제권한 없음");
      }
   }
}