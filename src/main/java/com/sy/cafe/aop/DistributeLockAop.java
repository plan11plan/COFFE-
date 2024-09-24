package com.sy.cafe.aop;

import com.sy.cafe.exception.ErrorCode;
import com.sy.cafe.exception.RequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributeLockAop {
    private static final String REDISSON_KEY_PREFIX = "RLOCK_";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;


    @Around("@annotation(com.sy.cafe.aop.DistributeLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        String key = REDISSON_KEY_PREFIX + createKey(signature.getParameterNames(), joinPoint.getArgs(), distributeLock.key());

        RLock rLock = redissonClient.getLock(key);
        boolean isLocked = false;
        try {
            isLocked = rLock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(), distributeLock.timeUnit());
            if (!isLocked) {
                log.error("Could not acquire lock for key: {{}}", key);
            }
            log.info("get distribution lock success {}" , key);
            return aopForTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new RequestException(ErrorCode.LOCK_INTERRUPTED);
        } finally {
            if (isLocked) {
                rLock.unlock();
                log.info("Released distributed lock for key: {}", key);
            }
        }
    }
    private String createKey(String[] parameterNames, Object[] args, String key) {
        String resultKey = key;

        /* key = parameterName */
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(key)) {
                resultKey += args[i];
                break;
            }
        }

        return resultKey;
    }
}
