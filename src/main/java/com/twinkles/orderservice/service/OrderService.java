package com.twinkles.orderservice.service;

import com.twinkles.orderservice.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
