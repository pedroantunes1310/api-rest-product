package com.example.market.http.controller;

import com.example.market.entity.Order;
import com.example.market.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> orderList(){
        return orderService.orderList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order orderSave(@RequestBody Order order){
        return orderService.orderSave(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void orderDelete(@PathVariable("id") Long id){
        orderService.orderById(id)
                .map(order -> {
                    orderService.orderDeleteById(order.getId()); //<- essa linha esta com erro no order
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void orderUpdate(@PathVariable("id") Long id, @RequestBody Order order){
        orderService.orderById(id)
                .map(orderBase ->{
                    modelMapper.map(order, orderBase);
                    orderService.orderSave(orderBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
    }
    
}
