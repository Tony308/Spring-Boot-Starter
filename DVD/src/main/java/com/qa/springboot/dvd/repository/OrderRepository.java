package com.qa.springboot.dvd.repository;

import com.qa.springboot.dvd.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    Page<OrderModel> findByUserId(Long personId, Pageable pageable);


}