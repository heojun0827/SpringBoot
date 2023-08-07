package com.tjoeun.dto;

import org.modelmapper.ModelMapper;

import com.tjoeun.entity.ItemImg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ItemImgDto {
	
  private Long id;
	
	private String imgName;     // 이미지 파일명 : project 내에서 UUID 로 저장되는 이름
	
	private String oriImgName;  // 원본 이미지 파일명
	
	private String imgUrl;      // 이미지 저장 경로
	
	private String repImgYn;    // 대표 이미지 여부
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	// ItemImgDto 가 화면에서 받아온 data 를
	// Entity 클래스인 ItemImg 에 전달하는 메소드
	public static ItemImgDto of(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class);
	}
	
	
}



