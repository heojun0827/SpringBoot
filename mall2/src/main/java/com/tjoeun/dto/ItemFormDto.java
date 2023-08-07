package com.tjoeun.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.tjoeun.constant.ItemSellStatus;
import com.tjoeun.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemFormDto {
	
	private Long id;                    // 상품 코드
	
	@NotBlank(message = "상품이름은 반드시 입력해 주세요")
	private String itemNm;              // 상품 이름
	
	@NotNull(message = "가격은 반드시 입력해 주세요")
	private Integer price;                  // 상품 가격 
	
	@NotNull(message = "재고 수량은 반드시 입력해 주세요")
	private Integer stockNumber;            // 재고 수량
	
	@NotBlank(message = "상품 상세 설명은 반드시 입력해 주세요")
  private String itemDetail;          // 상품 상세 설명	
	
	private ItemSellStatus itemSellStatus;    // 상품 판매 상태
	
	// 이미지 리스트 : 이미지 받아오기
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
	
	// 이미지 번호 받아오기
	private List<Long> itemImgIds = new ArrayList<>();
	
	// Item Entity 와 ItemFromDto Mapping 하기
	private static ModelMapper modelMapper = new ModelMapper();
	
	// ItemFromDto 가 받은 값을 Item Entity 에 저장하기
	// 화면에 입력한 값을 DB 에 저장함 - 등록 / 수정
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	
  // Item Entity 에 있는 값을 ItemFormDto 에 저장해서 return 함
	// DB 에 있는 값을 화면에 출력함 - 수정할 때
	public ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
	
}





