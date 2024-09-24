package com.sy.cafe.controller;

import com.sy.cafe.dto.request.MenuRequestDto;
import com.sy.cafe.dto.response.MenuResponseDto;
import com.sy.cafe.dto.response.PopularMenuDto;
import com.sy.cafe.global.ResponseDto;
import com.sy.cafe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    // 전체 메뉴 조회
    @GetMapping
    public ResponseDto<List<MenuResponseDto>> allMenu(){
        return ResponseDto.success(menuService.allMenu());
    }

    // 메뉴 추가
    @PostMapping
    public ResponseDto<MenuResponseDto> addMenu(@RequestBody MenuRequestDto dto){
        return ResponseDto.success(menuService.addMenu(dto.getName(),dto.getPrice()));
    }

    // 메뉴 수정
    @PostMapping("/{menuId}")
    public ResponseDto<MenuResponseDto> updateMenu(@PathVariable Long menuId, @RequestBody MenuRequestDto dto){
        return ResponseDto.success(menuService.updateMenu(menuId, dto.getName(),dto.getPrice()));
    }
    // 인기 메뉴
    @GetMapping("/popular")
    public ResponseDto<List<PopularMenuDto>> popularMenu(){
        return ResponseDto.success(menuService.popularMenu());
    }

}
