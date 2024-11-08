package com.ijse.springintro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.dto.OrderDTO;
import com.ijse.springintro.entity.Order;
import com.ijse.springintro.entity.Product;
import com.ijse.springintro.service.OrderService;
import com.ijse.springintro.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@RestController

public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(OrderDTO orderDTO){
        Order order = new Order();

        //get product ids from order dto
        List<Long> productIds = orderDTO.getProductIds();
        productIds.forEach(productId -> {
            //get product by the product id
            Product product = productService.getProductById(productId);

            //add this product to order
            if (product != null) {
                order.getOrderedProducts().add(product);

                //set order's total price
                order.setTotalPrice(order.getTotalPrice() + product.getPrice());
            }

        });

        orderService.createOrder(order);
        return ResponseEntity.status(201).body(order);
    }
    
    
    
    
}
