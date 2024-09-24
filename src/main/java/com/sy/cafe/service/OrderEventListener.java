package com.sy.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderEventListener {
//    private final DataTransferService dataTransferService;
    private final KafkaProducerService kafkaProducerService;

//    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderService.OrderEvent event) {
        kafkaProducerService.sendOrderData(event.getOrderData());
//        dataTransferService.sendOrderData(event.getOrderData());
    }
}
