package com.sy.cafe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    BALANCE_INSUFFICIENT(HttpStatus.BAD_REQUEST, "포인트가 부족합니다."),

    DUPLICATED_REQUEST(HttpStatus.BAD_REQUEST, "중복된 요청입니다."),
    LOCK_INTERRUPTED(HttpStatus.BAD_REQUEST, "락 획득 중 인터럽트 발생"),
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 데이터입니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
