package com.sy.cafe.service;

import com.sy.cafe.aop.DistributeLock;
import com.sy.cafe.domain.Order;
import com.sy.cafe.dto.OrderDataDto;
import com.sy.cafe.dto.OrderDto;
import com.sy.cafe.dto.OrderItemDto;
import com.sy.cafe.dto.response.OrderResponseDto;
import com.sy.cafe.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService{
    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;


    @DistributeLock(key = "userId")
    public OrderResponseDto orderMenu(Long userId, List<OrderDto> orderList) {

        // 주문 메뉴 리스트
        List<OrderItemDto> orderItemList = menuService.orderedMenu(orderList);

        // 주문 총액
        long totalAmount = orderItemList.stream().mapToLong(i -> i.getPrice() * i.getNumber()).sum();

        // 유저 주문 가능 여부 확인, 결제
        long currentBalance = userService.order(userId, totalAmount);

        // 주문 생성
        Order order = Order.createOrder(totalAmount, userId, orderItemList);
        orderRepository.save(order);

        // 데이터 수집 플랫폼에 전송
        eventPublisher.publishEvent(new OrderEvent(new OrderDataDto(order)));

        return OrderResponseDto.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .currentPoint(currentBalance)
                .orderList(orderList)
                .build();
    }


    public static class OrderEvent{
        @Getter
        private OrderDataDto orderData;

        public OrderEvent(OrderDataDto orderData) {
            this.orderData = orderData;
        }
    }
}
