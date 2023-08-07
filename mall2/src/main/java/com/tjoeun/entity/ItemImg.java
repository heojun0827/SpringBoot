package com.tjoeun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tjoeun.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter 
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemImg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_img_id")
	private Long id;
	
	private String imgName;     // 이미지 파일명 : project 내에서 UUID 로 저장되는 이름
	
	private String oriImgName;  // 원본 이미지 파일명
	
	private String imgUrl;      // 이미지 저장 경로
	
	private String repImgYn;    // 대표 이미지 여부
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	// 이미지를 변경하는 경우
	public void updateImg(String imgName, String oriImgName, String imgUrl) {
		this.imgName = imgName;
		this.oriImgName = oriImgName;
		this.imgUrl = imgUrl;
	}
	
	
}




