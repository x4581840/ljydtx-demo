package com.demo.redis.distribute_lock;

import com.demo.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 4:12 PM
 * @Version 1.0
 **/
@Component
public class RedisDistributeLockImpl implements RedisDistributeLock {
    private static Logger logger = LoggerFactory.getLogger(RedisDistributeLockImpl.class);

    public static final String SEP = ":";
    public static final String REDIS_NAMESPACE = "com:hand:zhishinet:common:redis:lock";
    //@Value("${redis.lock.defaultWaitTime:120}")
    private int defaultWaitTime;
    //@Value("${redis.lock.defaultTimeout:300}")
    private int defaultTimeout;
    //@Value("${redis.lock.defaultWaitInterval: 1}")
    private int defaultWaitInterval;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void lock(final String lock) {
        lock(lock, defaultWaitTime, defaultTimeout);
    }

    @Override
    public void lock(final String lock, final int waitTime, final int timeout) {
        int time = waitTime;
        while (isLocked(lock) && time > 0) {
            try {
                Thread.sleep(defaultWaitInterval * 1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(String.format("无法获取分布式锁：%s", lock));
            }
            time -= defaultWaitInterval;
        }
        if (!doLock(lock, timeout)) {
            throw new LockWaitTimeoutException(String.format("获取锁%s超时", lock));
        }
    }

    private boolean doLock(final String lock, final int timeout) {
        BoundValueOperations<String, Date> op = redisTemplate.boundValueOps(getLock(lock));
        if (op.setIfAbsent(TimeUtils.getCurrentUTCDate())) {
            op.expire(timeout, TimeUnit.SECONDS);
            if (logger.isDebugEnabled()) {
                logger.debug("create lock {} with timeout {}", lock, timeout);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tryLock(final String lock) throws AcquireLockException {
        tryLock(lock, defaultTimeout);
    }

    @Override
    public void tryLock(final String lock, final int timeout) throws AcquireLockException {
        if (!doLock(lock, timeout)) {
            throw new AcquireLockException(String.format("获取锁%s失败", lock));
        }
    }

    @Override
    public void unlock(String lock) {
        redisTemplate.delete(getLock(lock));
        if (logger.isDebugEnabled()) logger.debug("unlock {}", lock);
    }

    @Override
    public boolean isLocked(final String lock) {
        return Objects.nonNull(redisTemplate.boundValueOps(getLock(lock)).get());
    }

    private String getLock(String lock) {
        return REDIS_NAMESPACE + SEP + lock;
    }


}
