package com.tjoeun.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.tjoeun.entity.Member;
import com.tjoeun.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional //오류가나면 자동으로롤백
@Slf4j
class MemberTest {

   @Autowired
   private MemberRepository memberRepository;
   
   @Autowired
   private EntityManager entityManager;
   
   //@WithMockUser -  가상의 유저(모형)를 만든다는 의미
   @Test
   @DisplayName("auditing test")
   @WithMockUser(username="고양이",roles="ADMIN")
   void auditingTest() {
      Member member = new Member();
      memberRepository.save(member);
      // persistence context 의 변경 내용을 db에 반영
      entityManager.flush();
      // persistence context를 초기화한다
      entityManager.clear();
      
      Member foundMember = memberRepository.findById(member.getId())
                                  .orElseThrow(EntityNotFoundException::new);
      
      log.info("등록일 : " + foundMember.getRegTime());
      log.info("수정일 : " + foundMember.getUpdateTime());
      log.info("작성자 : " + foundMember.getCreatedBy());
      log.info("수정자 : " + foundMember.getModifiedBy());
   }

}