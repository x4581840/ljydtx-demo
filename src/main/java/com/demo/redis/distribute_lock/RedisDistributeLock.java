package com.demo.redis.distribute_lock;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 4:10 PM
 * @Version 1.0
 **/
public interface RedisDistributeLock {
    /**
     * 获得锁，默认超时时间为5min
     *
     * @param lock 锁名称
     */
    void lock(String lock);

    /**
     * 获得锁
     *
     * @param lock    锁名称
     * @param timeout 锁超时时间,单位: s
     */
    void lock(String lock, int waitTime, int timeout);

    void tryLock(String lock) throws AcquireLockException;

    void tryLock(String lock, int timeout) throws AcquireLockException;

    /**
     * 解除锁
     */
    void unlock(String lock);

    /**
     * 判断锁状态
     *
     * @param lock 锁名称
     * @return
     */
    boolean isLocked(String lock);
}
