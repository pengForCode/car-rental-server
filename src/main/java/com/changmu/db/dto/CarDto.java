package com.changmu.db.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28 11:19
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String id;

    /**
     * 车牌号
     */
    private String carCode;


    /**
     * 车辆状态
     */
    private String status;


    /**
     * 描述
     */
    private String description;
}
