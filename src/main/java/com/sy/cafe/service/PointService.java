package com.sy.cafe.service;

import com.sy.cafe.domain.PointHistory;
import com.sy.cafe.domain.PointType;
import com.sy.cafe.domain.User;
import com.sy.cafe.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;

    @Transactional
    public void chargePoint(Long chargeAmount, User user) {
        // 포인트 충전 이력 추가
        PointHistory pointHistory = PointHistory.builder()
                .type(PointType.CHARGE)
                .user(user)
                .point(chargeAmount)
                .build();
        pointRepository.save(pointHistory);

    }

    @Transactional
    public void usePoint(Long orderAmount, User user){
        // 포인트 사용 이력 추가
        PointHistory pointHistory = PointHistory.builder()
                .type(PointType.PAYMENT)
                .user(user)
                .point(orderAmount)
                .build();
        pointRepository.save(pointHistory);
    }

}
