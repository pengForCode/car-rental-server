package com.changmu.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chang mu
 * @version 1.0
 * @date 2022/4/27
 * @description 租车单
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_rent_order")
public class RentOrder {
    /**
     * 租车单id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 租车用户
     */
    private String userId;

    /**
     * 车辆id
     */
    private String carId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
