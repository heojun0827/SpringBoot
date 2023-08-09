package com.tjoeun.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dto.ItemFormDto;
import com.tjoeun.dto.ItemSearchDto;
import com.tjoeun.entity.Item;
import com.tjoeun.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;

	// 상품 등록
	@GetMapping("/admin/item/new")
  public String intemForm(ItemFormDto itemFormDto) {
		return "item/itemForm";
	}
	
	/*
	@GetMapping("/admin/item/new")
  public String intemForm(ItemFormDto itemFormDto, Model model) {
		model.addAttribute("itemFormDto", itemFormDto);
		return "item/itemForm";
	}
	
	@GetMapping("/admin/item/new")
	public String intemForm(Model model) {
		model.addAttribute("itemFormDto", new ItemFormDto());
		return "item/itemForm";
	}
	*/
	
	// 상품 등록
	@PostMapping("/admin/item/new")
	public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult result, Model model,
			                  @RequestParam("itemImgFileList") List<MultipartFile> itemImgFileList) {
		
		if(result.hasErrors()) {
			return "item/itemForm";
		}
		
		// 상품 이미지를 선택하지 않고 상품저장을 누른 경우
		// 상품 이미지는 최소한 하나는 올려야 되도록 함
		if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫 번째 상품 이미지는 반드시 업로드하셔야 합니다");
			return "item/itemForm";
		}
		
		try {
			itemService.saveItem(itemFormDto, itemImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 오류가 발생했습니다");
			return "item/itemForm";
		}
		
		return "redirect:/";
	}
	
	// 상품 수정
	@GetMapping("/admin/item/{itemId}")
	public String getItemDetail(@PathVariable("itemId") Long itemId, Model model) {
		
		try {
  		ItemFormDto itemFormDto = itemService.getItemDetail(itemId);
  		model.addAttribute("itemFormDto", itemFormDto);
  		
		}catch(EntityNotFoundException e) {
			model.addAttribute("errorMessage", "등록되지 않은 상품입니다");
			model.addAttribute("itemFormDto", new ItemFormDto());
			return "item/itemForm";
			
		}
		
		return "item/itemForm";
	}
	
  // 상품 수정
	@PostMapping("/admin/item/{itemId}")
	public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult result, Model model,
                           @RequestParam("itemImgFileList") List<MultipartFile> itemImgFileList) {
	
		if(result.hasErrors()) {
			return "item/itemForm";
			
		}
		
	  // 상품 이미지를 선택하지 않고 상품저장을 누른 경우
	  // 상품 이미지는 최소한 하나는 올려야 되도록 함
		if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫 번째 상품 이미지는 반드시 업로드하셔야 합니다");
			return "item/itemForm";
		}
		
		try {
			itemService.updateItem(itemFormDto, itemImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 오류가 발생했습니다");
			return "item/itemForm";
		}
		
		return "redirect:/";

	}
	
	@GetMapping({"/admin/items", "/admin/items/{page}"})
	public String itemList(ItemSearchDto itemSearchDto, Model model,
			                   @PathVariable("page") Optional<Integer> page) {
		
		// Pageable : page 정보를 담고 있는 객체
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);
		Page<Item> items  = itemService.getAdminItemPage(itemSearchDto, pageable);
		
		model.addAttribute("items", items);
		model.addAttribute("itemSearchDto", itemSearchDto);
		
		// 페이지 최대 사이즈
		model.addAttribute("maxPage", 5);
		
		
		
		return "item/itemList";
	}
	
}





