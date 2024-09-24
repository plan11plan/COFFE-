package com.sy.cafe.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
public abstract class CreatedTimeEntity {
    @CreatedDate
    private LocalDateTime createdTime;
}
