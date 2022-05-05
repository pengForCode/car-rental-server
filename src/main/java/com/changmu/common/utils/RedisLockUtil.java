package com.changmu.common.utils;

import com.changmu.common.utils.utilInterface.DistributedLocker;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/27
 * @description redis锁帮助类
 */
@Slf4j
public class RedisLockUtil {

    private static final DistributedLocker DISTRIBUTED_LOCKER = SpringUtil.getBean(DistributedLocker.class);

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        return DISTRIBUTED_LOCKER.lock(lockKey);
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public static void unlock(String lockKey) {
        DISTRIBUTED_LOCKER.unlock(lockKey);
    }

    /**
     * 释放锁
     * @param lock
     */
    public static void unlock(RLock lock) {
        DISTRIBUTED_LOCKER.unlock(lock);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public static RLock lock(String lockKey, int timeout) {
        return DISTRIBUTED_LOCKER.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public static RLock lock(String lockKey, int timeout,TimeUnit unit ) {
        return DISTRIBUTED_LOCKER.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return DISTRIBUTED_LOCKER.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return DISTRIBUTED_LOCKER.tryLock(lockKey, unit, waitTime, leaseTime);
    }

}
