package com.sy.cafe.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateLockAop {
    private static final String REDISSON_KEY_PREFIX = "RLOCK_";

    private final RedissonClient redissonClient;
    @Around("@annotation(com.sy.cafe.aop.UpdateLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {

        RLock rLock = redissonClient.getLock(REDISSON_KEY_PREFIX + "PopularMenu");

        try {
            if (!rLock.tryLock(0, 3,  TimeUnit.SECONDS)) {
                return false;
            }
            log.info("get lock success");
            return joinPoint.proceed();
        } catch (Exception e) {
            throw new InterruptedException();
        } finally {
            rLock.unlock();
        }
    }
}
