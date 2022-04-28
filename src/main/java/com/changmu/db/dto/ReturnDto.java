package com.changmu.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28 15:33
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDto {

    /**
     * 出租单id
     */
    @NotNull
    private String rentOrderId;


}
