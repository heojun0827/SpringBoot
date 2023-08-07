package com.tjoeun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjoeun.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
