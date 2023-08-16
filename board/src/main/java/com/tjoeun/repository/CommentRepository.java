package com.tjoeun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjoeun.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
