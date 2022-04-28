package com.changmu.common.utils.utilInterface;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
/**
 * @author chang mu
 * @version 1.0
 * @date 2022/4/27
 * @description redis分布式锁接口
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int leaseTime);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}
