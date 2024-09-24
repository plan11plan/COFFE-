package com.sy.cafe.controller;

import com.sy.cafe.dto.request.OrderRequestDto;
import com.sy.cafe.dto.response.OrderResponseDto;
import com.sy.cafe.global.ResponseDto;
import com.sy.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseDto<OrderResponseDto> orderMenu(@RequestBody OrderRequestDto dto){
        return ResponseDto.success(orderService.orderMenu(dto.getUserId(), dto.getOrderList()));
    }
}
