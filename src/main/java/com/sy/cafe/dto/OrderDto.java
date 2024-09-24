package com.sy.cafe.dto;

import com.sy.cafe.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    Long menuId;
    int number;

    public OrderDto(OrderItem orderItem){
        this.menuId = orderItem.getMenuId();
        this.number = orderItem.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OrderDto))
            return false;
        OrderDto dto = (OrderDto)o;
        return Objects.equals(menuId, dto.menuId) &&
                Objects.equals(number, dto.number);
    }
}
