package com.tjoeun.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Test {

	public static void main(String[] args) {
		
		
		Student s1 = Student.builder()
												.name("더조은")
												.height(180)
												.build();
		System.out.println("s1 : " +s1);
		// Student s = new Student("더조은", 180);
		
		Student s2 = Student.builder()
                				.name("아이티")
                				.build();
		System.out.println("s2 : " +s2);
	}
	
}

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
class Student{
	private String name;
	private int age;
	private int height;
	private int weight;
	
	/*
	 * @Override public String toString() { return this.name + " - " + this.age +
	 * " - " + this.height + " - " + this.weight + " - "; }
	 */
}
