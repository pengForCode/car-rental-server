package com.changmu.service;

import com.changmu.db.dto.CarDto;
import com.changmu.db.dto.RentDto;
import com.changmu.db.dto.ReturnDto;
import com.changmu.db.entity.Car;
import com.changmu.db.entity.RentOrder;

import java.text.ParseException;
import java.util.List;

/**
 * @author chang mu
 * @version 1.0
 * @date 2022/4/27
 * @description
 */
public interface CarService {

    /**
     * 查询所有car
     * @return
     */
    List<Car> getAllCars();

    /**
     * 增加车辆
     * @param carDto 添加信息
     * @return 添加结果
     */
    int addCar(CarDto carDto);

    /**
     * 修改车辆
     * @param carDto 修改信息
     * @return 修改结果
     */
    int updateCar(CarDto carDto);

    /**
     * 删除车辆
     * @param carDto 删除信息
     * @return 删除结果
     */
    int removeCar(CarDto carDto);

    /**
     * 租赁汽车
     * @param rentDto 租赁信息
     * @return 租赁结果
     * @throws ParseException
     */
    int rentCar(RentDto rentDto) throws ParseException;

    /**
     * 手动还车
     * @param returnDto 还车信息
     * @return 还车结果
     */
    int returnCar(ReturnDto returnDto);

    /**
     * 通过用户id查询对应出租单
     * @param userId 用户id
     * @return 出租单列表
     */
    List<RentOrder> getRentOrderByUserId(String userId);

}
