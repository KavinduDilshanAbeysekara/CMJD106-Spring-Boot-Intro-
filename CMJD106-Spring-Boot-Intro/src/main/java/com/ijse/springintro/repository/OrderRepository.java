package com.ijse.springintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.springintro.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
