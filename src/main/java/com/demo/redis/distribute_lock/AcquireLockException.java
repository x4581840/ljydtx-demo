package com.demo.redis.distribute_lock;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 4:11 PM
 * @Version 1.0
 **/
public class AcquireLockException extends Exception {
    public AcquireLockException() {
    }

    public AcquireLockException(final String message) {
        super(message);
    }
}
