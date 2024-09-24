package com.sy.cafe.repository;

import com.sy.cafe.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBycreatedTimeAfter(LocalDateTime datetime);
}
