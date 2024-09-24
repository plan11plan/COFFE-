package com.sy.cafe.dto.request;

import com.sy.cafe.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    Long userId;
    List<OrderDto> orderList;
}
