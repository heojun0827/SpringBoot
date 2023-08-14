package com.tjoeun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjoeun.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
  
	Optional<Users> findByusername(String username);
}
