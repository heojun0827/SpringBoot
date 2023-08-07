package com.tjoeun.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value= {AuditingEntityListener.class})
@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
	
  //처음 작성한 사람
	@CreatedBy
  //수정이 안 되게 설정하기
	@Column(updatable=false)
	private String createdBy;
	
  // 마지막으로 수정한 사람
	@LastModifiedBy
	private String modifiedBy;
	
  //처음 작성된 날 가져오기
	@CreatedDate
  //수정이 안 되게 설정하기
	@Column(updatable=false)
	private LocalDateTime regTime;
	
	// @LastModifiedDate : 마지막 수정일
	@LastModifiedDate
	private LocalDateTime updateTime;
	

}
