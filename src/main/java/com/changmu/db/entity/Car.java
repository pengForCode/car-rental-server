package com.changmu.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chang mu
 * @version 1.0
 * @date 2022/4/27 20:19
 * @description 车辆表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_car")
public class Car {

    /**
     * 车辆id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 车牌号
     */
    private String carCode;


    /**
     * 车辆状态(0-空闲状态，1-已出租)
     */
    private String status;

    /**
     * 描述
     */
    private String description;

}
