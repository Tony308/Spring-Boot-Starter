package com.qa.springboot.dvd.controller;

import com.qa.springboot.dvd.model.DvdModel;
import com.qa.springboot.dvd.model.OrderModel;
import com.qa.springboot.dvd.model.UserModel;
import com.qa.springboot.dvd.repository.DvdRepository;
import com.qa.springboot.dvd.repository.OrderRepository;
import com.qa.springboot.dvd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/order ")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DvdRepository dvdRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}/orders")
    public Page<OrderModel> findByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable){
        return orderRepository.findByUserId(userId,pageable);
    }
}
