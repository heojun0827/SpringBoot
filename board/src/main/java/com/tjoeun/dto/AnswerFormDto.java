package com.tjoeun.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class AnswerFormDto {

	@NotEmpty(message="답변 내용은 반드시 입력해 주세요")
	private String content;
	
}
