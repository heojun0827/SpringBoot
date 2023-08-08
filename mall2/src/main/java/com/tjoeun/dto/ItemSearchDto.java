package com.tjoeun.dto;

import com.tjoeun.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;

/*
    조회 조건  1) 상품등록일
               2) 판매상태
               3) 상품명
               4) 등록자 아이디
    을 화면에 입력하면
    이 값을 받아오는 DTO              
*/

@Getter @Setter
public class ItemSearchDto {
	
	private String searchDateType;
	
	private ItemSellStatus searchSellStatus;
	
	private String searchBy;
	
	private String searchQuery = "";

}











