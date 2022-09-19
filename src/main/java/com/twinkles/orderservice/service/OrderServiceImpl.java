package com.twinkles.orderservice.service;

import com.twinkles.orderservice.dto.OrderLineItemsDto;
import com.twinkles.orderservice.dto.OrderRequest;
import com.twinkles.orderservice.model.Order;
import com.twinkles.orderservice.model.OrderLineItems;
import com.twinkles.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
       Order order = new Order();
       order.setOrderNumber(UUID.randomUUID().toString());
       List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsList().stream()
               .map(this::mapToOrderLineItems).toList();

       order.setOrderLineItemsList(orderLineItemsList);

       orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .id(orderLineItemsDto.getId())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }


}
