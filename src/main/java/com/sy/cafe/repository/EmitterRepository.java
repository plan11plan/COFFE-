package com.sy.cafe.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    SseEmitter save(String emitterId, SseEmitter sseEmitter);
    Map<String, SseEmitter> findAllEmitters(String emitterId);
    void deleteById(String emitterId);
}
