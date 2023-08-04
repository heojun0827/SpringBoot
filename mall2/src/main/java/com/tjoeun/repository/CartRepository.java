package com.tjoeun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjoeun.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
