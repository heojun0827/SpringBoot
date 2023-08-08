package com.tjoeun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjoeun.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>{
	
	// Query Method
	
	// item image 들을 list 로 가져오기
	List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
	
}
