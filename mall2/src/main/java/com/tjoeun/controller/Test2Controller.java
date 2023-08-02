package com.tjoeun.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dto.ItemDto;
import com.tjoeun.test.Person;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/test")
@Log4j2
public class Test2Controller {
	
	@GetMapping("/t1")
	public void t1(Person person, Model model) {
		
		person.setName("더조은");
		person.setHeight(188);
		
		model.addAttribute("text", "String Boot 2.7.14");
		// model.addAttribute("person", person);
	}
	
	@GetMapping("/t2")
	public void t2(ItemDto itemDto) {
		
		itemDto.setItemDetail("상세설명");
		itemDto.setItemNm("상품1");
		itemDto.setPrice(30000);
		itemDto.setRegTime(LocalDateTime.now());
		
	}
	
	@GetMapping({"/t3", "/t4"})
	public void t3(ItemDto itemDto, Model model) {
		
		List<ItemDto> itemList = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			itemDto.setItemDetail("상세설명-"+ (i+1));
			itemDto.setItemNm("상품-"+ (i+1));
			itemDto.setPrice(30000);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemList.add(itemDto);
		}
		model.addAttribute("itemList",itemList);
	}
	
  @GetMapping("/t5")
  public void t5(String name, String height, Model model) { 
  	log.info(">>>>>>>>>>>> " + name + ", " + height);
  	model.addAttribute("name", name);
  	model.addAttribute("height", height);
  }
  
  @GetMapping({"/content1", "/content2"})
  public void content() {
  	
  }
	
}

