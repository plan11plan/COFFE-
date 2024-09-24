package com.sy.cafe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sy.cafe.dto.OrderDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public void sendOrderData(OrderDataDto orderData){
        String topic = "orderData";
        try{
            String orderDataJson = mapper.writeValueAsString(orderData);
            kafkaTemplate.send(topic, orderDataJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
