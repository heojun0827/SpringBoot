package com.tjoeun.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tjoeun.constant.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
public class Orders {

	@Id
	@Column(name = "orders_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	private LocalDateTime orderDate;
	
	private OrderStatus orderStatus;
	
	private LocalDateTime regTime;
	
	private LocalDateTime updateTime;
	
}









