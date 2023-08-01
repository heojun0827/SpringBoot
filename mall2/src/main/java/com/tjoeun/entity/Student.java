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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// Entity class 의 멤버벼ㄴ수 이름과 table 의 column 이름을 다르게 하는경우
	@Column(name="name", nullable=false, length=30)
	// java camel case --> db snake case
	private String myName;
	private int myHeight;
}
