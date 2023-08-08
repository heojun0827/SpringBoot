package com.tjoeun.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tjoeun.dto.ItemSearchDto;
import com.tjoeun.entity.Item;

public interface ItemCustomRepository {
	
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
