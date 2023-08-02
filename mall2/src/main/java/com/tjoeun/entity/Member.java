package com.tjoeun.entity;

import javax.persistence.Entity;

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
public class Member {
	
	private String name;
	private String email;
	private String password;
	private String address;
}
