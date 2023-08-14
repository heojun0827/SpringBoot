package com.tjoeun.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tjoeun.entity.Users;
import com.tjoeun.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
	
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  
  public Users createUsers(String username, String password, String email) {
    // 비밀번호 암호화하기
   	// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   	String encodedPassword = passwordEncoder.encode(password);
   	
  	Users users = new Users();
  	users.setUsername(username);
  	
  	// 암호화된 비밀번호를 설정하기
  	users.setPassword(encodedPassword);
  	users.setEmail(email);
  	
  	// Controller 에서 전달받은 값으로 설정된
  	// Users Entity 를 DB 에 저장하기  <--  insert
  	usersRepository.save(users);
  	
  	return users;
  }
  
  public Users getUsers(String username) {
  	
  	Users users = usersRepository.findByusername(username)
  			                         .orElseThrow(() -> new EntityNotFoundException("해당 회원이 없습니다"));
  	return users;
  }
  	
}
  





