package com.changmu.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    /**
     * 用户id
     */
    @NotNull
    private String userId;

    /**
     * 车辆id
     */
    @NotNull
    private String carId;

    /**
     * 开始时间
     */
    @NotNull
    private String startTime;

    /**
     * 结束时间
     */
    @NotNull
    private String endTime;
}
