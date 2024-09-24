package com.sy.cafe.controller;

import com.sy.cafe.dto.request.PointRequestDto;
import com.sy.cafe.dto.request.UserRegisterDto;
import com.sy.cafe.dto.response.PointResponseDto;
import com.sy.cafe.dto.response.UserResponseDto;
import com.sy.cafe.global.ResponseDto;
import com.sy.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseDto<UserResponseDto> register(@RequestBody UserRegisterDto dto){
        return ResponseDto.success(userService.register(dto.getNickname()));
    }

    @PostMapping("/point/charge")
    public ResponseDto<PointResponseDto> chargePoint(@RequestBody PointRequestDto dto){
        return ResponseDto.success(userService.chargePoint(dto.getUserId(), dto.getPoint()));
    }

}
