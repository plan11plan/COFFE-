package com.sy.cafe.dto;

import com.sy.cafe.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataDto {
    Long userId;
    List<OrderDto> orderItems;
    Long totalAmount;

    public OrderDataDto(Order order){
        userId = order.getUserId();
        orderItems = order.getOrderItems().stream().map(OrderDto::new).collect(Collectors.toList());
        totalAmount = order.getAmount();
    }

}
