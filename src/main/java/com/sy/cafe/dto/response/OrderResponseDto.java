package com.sy.cafe.dto.response;

import com.sy.cafe.dto.OrderDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    Long userId;
    Long currentPoint;
    List<OrderDto> orderList;
    Long totalAmount;

    @Builder
    public OrderResponseDto(Long userId, Long currentPoint, List<OrderDto> orderList, Long totalAmount) {
        this.userId = userId;
        this.currentPoint = currentPoint;
        this.orderList = orderList;
        this.totalAmount = totalAmount;
    }
}
