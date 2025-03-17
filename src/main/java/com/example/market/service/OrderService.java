package com.example.market.service;

import com.example.market.entity.Order;
import com.example.market.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order orderSave(Order order){
        return orderRepository.save(order);
    }

    public List<Order> orderList(){
        return orderRepository.findAll();
    }

    public Optional<Order> orderById(Long id){
        return orderRepository.findById(id);
    }

    public void orderDeleteById(Long id){
        orderRepository.deleteById(id);
    }

}
