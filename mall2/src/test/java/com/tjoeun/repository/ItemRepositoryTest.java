package com.tjoeun.repository;

import static com.tjoeun.entity.QItem.item;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tjoeun.constant.ItemSellStatus;
import com.tjoeun.entity.Item;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
//@Transactional 이것은 테스트를하고 커밋을 하지않고 다시 롤백한다
@Log4j2
class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	public void createItemList() {
		for(int i=1;i<=10;i++) {
			Item item = new Item();
			item.setItemNm("상품"+i);
			item.setPrice(10000+i*100);
			item.setItemDetail("상품상세설명"+i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("상풍명조회테스트")
	public void findByItemNmtest() {
		createItemList();
		List<Item> itemList = itemRepository.findByItemNm("상품10");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
  @Test
  @DisplayName("상품명, 상품상세설명 or 테스트")
  public void findByItemNmOrItemDetailTest() {
     this.createItemList();
     List<Item> itemList =itemRepository.findByItemNmOrItemDetail("상품1", "상품상세설명5");
     System.out.println("\n ----------------------ItemNm이나 ItemDetail에 해당하는 상품 가져오기시작-------------------");
     for(Item item : itemList) {
        System.out.println(item.toString());
     }
     System.out.println("\n ----------------------ItemNm이나 ItemDetail에 해당하는 상품 가져오기종료-------------------");
  }
  
  @Test
  @DisplayName("JPQL 쿼리")
  public void findByItemDetailTest() {
  	this.createItemList();
  	
  	List<Item> itemList = itemRepository.findByItemDetail("상세설명");
  	
    System.out.println("\n ----------------------ItemDetail에 나온 단어 중 일부에 해당하는 상품 가져오기시작-------------------");
    for(Item item : itemList) {
       System.out.println(item);
       log.info(item);
    }
    System.out.println("\n ----------------------ItemDetail에 나온 단어 중 일부에 해당하는 상품 가져오기종료-------------------");
  }
  
//네이티브쿼리
  @Test
  @DisplayName("Native 쿼리")
  public void findByItemDetailNativeTest() {
     createItemList();
     
     List<Item> itemList = itemRepository.findByItemDetailNative("상세설명");
     System.out.println("\n ----------------------Native로 ItemDetail에 나온 단어 중 일부에 해당하는 상품 가져오기시작-------------------");
     for(Item item : itemList) {
        System.out.println(item);
        //log.info(item);
     }
     System.out.println("\n ----------------------Native로 ItemDetail에 나온 단어 중 일부에 해당하는 상품 가져오기종료-------------------");      
  }

  @Test
  @DisplayName("querydsl 테스트")
  public void querydslTest() {
  	createItemList();
  	
  	JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
  	//QItem qItem = new QItem("i");
  	/*
  	 	select i from Item i where i.itemDetail like
  	 	%:itemDetail% order by i.price desc
  	 */
  	
  	//import static com.tjoeun.entity.QItem.item;
  	// item <-- static import 로 설정함
  	List<Item> selectedItem = jpaQueryFactory.select(item)
              								 .from(item)
              								 .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
              								 .where(item.itemDetail.like("%3%"))
              								 .orderBy(item.price.desc())
              								 .fetch();
  	System.out.println("\n ----------------------현재 판매 중인 상품 목록 가져오기 시작(querydsl)-------------------");
  	for(Item item : selectedItem) {
  		log.info(item);
  	}
  	System.out.println("\n ----------------------현재 판매 중인 상품 목록 가져오기 종료(querydsl)-------------------");
  }
  
  public void createItemList2() {
		for(int i=1;i<=5;i++) {
			Item item = new Item();
			item.setItemNm("상품"+i);
			item.setPrice(10000+i*100);
			item.setItemDetail("상품상세설명"+i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			itemRepository.save(item);
		}		
		for(int i=6;i<=10;i++) {
			Item item = new Item();
			item.setItemNm("상품"+i);
			item.setPrice(10000+i*100);
			item.setItemDetail("상품상세설명"+i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			itemRepository.save(item);
		}
	}
  
  @Test
  @DisplayName("querydsl 테스트-2")
  public void querydslTest2() {
     createItemList2();
     
     String itemDetail = "상세";
     int price = 10200;
     String itemSellStatus = "SELL";
     
     // static import 한 item 사용하기
     BooleanBuilder builder = new BooleanBuilder();
     builder.and(item.itemDetail.like("%"+itemDetail+"%"));
     builder.and(item.price.gt(price));   // gt 는 greaterthan(~보다 크다)
     
  // 판매 중인 상품이라면 - project 내 값을 비교함
 		if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
 			// 아래 조건을 없애면 상품상세설명에 '상세' 라는 문자열이 포함되고
 			// 가격이 10200 이상 (10300 ~ 11000) 인 조건 두 가지만 남음
 			// database 에서 비교함 : 판매 중 상품
 			// builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
 		}
 		
 		// of(시작페이지, 가져올페이지개수)
 		// PageRequest org.springframework.data.domain.PageRequest.of(int page, int size)
 		// of(0, 5) : 첫 번째 페이지에 5 개를 가져옴
 		// of(1, 5) : 두 번째 페이지에 5 개를 가져옴
 		Pageable pageable = PageRequest.of(1, 5);
     
     // Page<Item> org.springframework.data.querydsl.QuerydslPredicateExecutor.findAll(Predicate predicate, Pageable pageable)
     Page<Item> allData = itemRepository.findAll(builder, pageable);
     
     // 전체 개수 확인하기
     log.info("전채 개수 : " + allData.getTotalElements() + "개");
     
     List<Item> contents = allData.getContent();
     for(Item item2 : contents) {
        log.info("item2 : " + item2);
     }
  }
}