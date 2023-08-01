package com.tjoeun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.tjoeun.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>,
																				QuerydslPredicateExecutor<Item>{
	// findByItemNm() 메소드 <-- Query Method
	//find(EntityClass이름)By(멤버변수이름-DB의컬럼)
	List<Item> findByItemNm(String ItemNm);
	
	List<Item> findByItemNmOrItemDetail(String ItemNm, String ItemDetail);
	
	// JPQL
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	// Native Query
	@Query(value="select * from item i where i.item_detail like "
					+ "%:itemDetail% order by i.price desc", nativeQuery=true)
	List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
}

