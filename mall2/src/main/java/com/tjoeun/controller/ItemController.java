package com.tjoeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjoeun.dto.ItemDto;

@Controller
public class ItemController {

	@GetMapping("/admin/item/new")
  public String intemForm(ItemDto itemDto) {
		return "item/itemForm";
	}
	
}
