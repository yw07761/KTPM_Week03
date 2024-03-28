package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
