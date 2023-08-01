package com.tjoeun.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.tjoeun.constant.ItemSellStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="item")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	//primary key
	@Id
	// auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Entity class 의 멤버변수 이름과 table 의 column 이름을 다르게 하는 경우
	@Column(name="item_id")
	private Long id;									// 상품 코드
	
	@Column(nullable=false, length=50)
	private String itemNm;						// 상품 이름
	
	@Column(nullable=false)
	private int price;								// 상품 가격
	
	// Entity class 의 멤버변수 이름과 table 의 column 이름을 다르게 하는 경우
	@Column(nullable=false, name="number")
	private int stockNumber;					// 재고 수량
	
	// @Lob : 길이가 255 개 이상인 문자열을 저장할 수 있음
	@Lob
	@Column(nullable=false)
	private String itemDetail;				// 상품 상세 설명
	
	@Enumerated(EnumType.STRING)		//숫자로 지정(ORDINAL)
	private ItemSellStatus itemSellStatus;
	
	private LocalDateTime regTime;		// 등록 시간
	
	private LocalDateTime updateTime;	// 수정 시간
}
