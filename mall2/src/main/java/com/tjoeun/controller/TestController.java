package com.tjoeun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.dto.TmpDto;

@RestController
public class TestController {
  /*
	@GetMapping("/")
	public String home() {
		return "Spring Boot !!!";
	}
	*/
	@GetMapping("/test1")
	public TmpDto test1() {
		
		TmpDto t1 = TmpDto.builder()
				              .name("더조은")
				              .height(182)
				              .build();		
		return t1;
	}
	
	@GetMapping("/test2")
	public TmpDto test2() {
		
		TmpDto t2 = TmpDto.builder()
              				.height(182)
              				.build();		
		return t2;
	}
	
	
}
