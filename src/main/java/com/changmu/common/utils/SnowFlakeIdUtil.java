package com.changmu.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28
 * @description 雪花算法id工具类
 */
public class SnowFlakeIdUtil {
    public static Long nextId(){
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        return snowflake.nextId();
    }

}
