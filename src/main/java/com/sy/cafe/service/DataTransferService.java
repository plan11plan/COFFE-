package com.sy.cafe.service;

import com.sy.cafe.dto.OrderDataDto;
import com.sy.cafe.exception.ErrorCode;
import com.sy.cafe.exception.RequestException;
import com.sy.cafe.repository.EmitterRepository;
import com.sy.cafe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataTransferService {
    private final OrderRepository orderRepository;
    private final EmitterRepository emitterRepository;

    private static final Long DEFAULT_TIMEOUT = 24L * 60 * 60 * 1000; // 24시간
    private static final String DATA_PLATFORM_ID = "DataPlatform_";
    public SseEmitter subscribe(String lastEventId) {
        String eventId = DATA_PLATFORM_ID + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(eventId, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> emitterRepository.deleteById(eventId));
        emitter.onTimeout(() -> emitterRepository.deleteById(eventId));

        sendToClient(emitter, eventId, "connected!!!", eventId);

        // 클라이언트가 미수신한 주문 목록이 존재할 경우
        if(!lastEventId.isEmpty()){
            Instant instant = Instant.ofEpochMilli(Long.parseLong(lastEventId.split("_")[1]));
            LocalDateTime datetime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            orderRepository.findBycreatedTimeAfter(datetime).forEach(
                    (order) -> {
                        sendToClient(emitter, eventId, new OrderDataDto(order), eventId);
                    }
            );
        }
        return emitter;
    }

    public void sendOrderData(OrderDataDto orderData){
        String eventId = DATA_PLATFORM_ID + System.currentTimeMillis();

        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitters(DATA_PLATFORM_ID);

        sseEmitters.forEach(
                (key, emitter) -> {
                    sendToClient(emitter, eventId, orderData, key);
                }
        );
    }

    private void sendToClient(SseEmitter emitter, String eventId, Object data, String emitterId) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("cafe order data")
                    .data(data));
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
            throw new RequestException(ErrorCode.BAD_REQUEST);
        }
    }

}
