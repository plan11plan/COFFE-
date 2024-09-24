package com.sy.cafe.repository;

import com.sy.cafe.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointHistory, Long> {
}
