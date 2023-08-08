package com.tjoeun.entity;

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
import com.tjoeun.dto.ItemFormDto;

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
public class Item extends BaseEntity {
	
	// primary key
	@Id
	// auto encreament
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Entity class의 멤버변수 이름과 table 의 column 이름을 다르게 하는 경우
	@Column(name="item_id")
	private Long id;                    // 상품 코드
	
	@Column(nullable=false, length=50)
	private String itemNm;              // 상품 이름
	
	@Column(nullable=false)
	private int price;                  // 상품 가격 
	
  // Entity class의 멤버변수 이름과 table 의 column 이름을 다르게 하는 경우
	@Column(nullable=false, name="number")
	private int stockNumber;            // 재고 수량
	
	// @Lob : 길이가 255 개 이상인 문자열을 저장할 수 있음
	@Lob
	@Column(nullable=false)
  private String itemDetail;          // 상품 상세 설명	
	
	// Enum 을 멤버변수로 사용할 수 있도록 해 주는 annotation
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	
  // item(Entity) 업데이트하기 : ItemFormDto 가 화면에서 받아온 값을 DB 테이블에 반영하기
	// DTO  --->  Entity
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price  = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail  = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
		
	}
	
	
}






