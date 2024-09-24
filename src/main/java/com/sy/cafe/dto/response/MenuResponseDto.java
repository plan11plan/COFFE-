package com.sy.cafe.dto.response;

import com.sy.cafe.domain.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MenuResponseDto {
    private Long id;
    private String name;
    private Long price;

    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
    }
}
