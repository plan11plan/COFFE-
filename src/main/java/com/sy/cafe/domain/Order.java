package com.sy.cafe.domain;

import com.sy.cafe.dto.OrderItemDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "orders")
public class Order extends CreatedTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Long amount; // 총 주문(결제) 가격

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Builder
    public Order(Long amount, Long userId) {
        this.amount = amount;
        this.userId = userId;
    }

    public static Order createOrder(Long totalAmount, Long userId, List<OrderItemDto> orderItemDtos){
        Order order = new Order();
        order.amount = totalAmount;
        order.userId = userId;
        order.orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            order.orderItems.add(new OrderItem(orderItemDto, order));
        }
        return order;
    }

}
