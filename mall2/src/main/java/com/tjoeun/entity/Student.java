package com.tjoeun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Entity 클래스이름과 table 이름을 다르게 하는 경우
// @Table(name="colleague")
@Entity
public class Student {
	
	// primary key
	@Id
	// auto encreament
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// Entity class의 멤버변수 이름과 table 의 column 이름을 다르게 하는 경우
	@Column(name="name", nullable=false, length=30)
	// java camel case --> db snake case
	private String myName;
	private int myHeight;
	
	
	
	

}
