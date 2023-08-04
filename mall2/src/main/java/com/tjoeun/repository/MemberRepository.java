package com.tjoeun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjoeun.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
  // query method
	Member findByEmail(String email);
	
	// Optional 을 사용하는 경우
	// Optional<Member> findByEmail(String email);
}
