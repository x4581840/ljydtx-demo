package com.demo.redis.distribute_lock;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 4:15 PM
 * @Version 1.0
 **/
public class LockWaitTimeoutException extends RuntimeException {
    public LockWaitTimeoutException() {
    }

    public LockWaitTimeoutException(final String message) {
        super(message);
    }
}