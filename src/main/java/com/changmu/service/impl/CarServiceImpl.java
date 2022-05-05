package com.changmu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changmu.common.constants.CarConstants;
import com.changmu.common.exception.RentalError;
import com.changmu.common.utils.DateUtils;
import com.changmu.common.utils.RedisLockUtil;
import com.changmu.common.utils.SnowFlakeIdUtil;
import com.changmu.db.dto.CarDto;
import com.changmu.db.dto.RentDto;
import com.changmu.db.dto.ReturnDto;
import com.changmu.db.entity.Car;
import com.changmu.db.entity.RentOrder;
import com.changmu.db.mapper.CarMapper;
import com.changmu.db.mapper.RentOrderMapper;
import com.changmu.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/27 22:16
 * @description
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CarServiceImpl implements CarService {


    @Autowired
    private CarMapper carMapper;

    @Autowired
    private RentOrderMapper rentOrderMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Car> getAllCars() {
        return carMapper.selectList(null);
    }

    @Override
    public int addCar(CarDto carDto) {
        Car car = new Car();
        BeanUtils.copyProperties(carDto,car);
        car.setId(SnowFlakeIdUtil.nextId().toString());
        car.setStatus(CarConstants.FREE);
        int result = carMapper.insert(car);
        log.info("添加车辆成功");
        return result;
    }

    @Override
    public int updateCar(CarDto carDto) {
        Car car = this.getCarExist(carDto.getId());
        BeanUtils.copyProperties(carDto,car);
        int result = carMapper.updateById(car);
        log.info("修改车辆成功");
        return result;
    }

    @Override
    public int removeCar(CarDto carDto) {
        this.getCarExist(carDto.getId());
        int result = carMapper.deleteById(carDto.getId());
        log.info("删除车辆成功");
        return result;
    }

    @Override
    public int rentCar(RentDto rentDto) throws ParseException {
        Car car = this.getCarExist(rentDto.getCarId());
        if (CarConstants.RENTED.equals(car.getStatus())){
            throw RentalError.CAR_RENT_ERROR.toException();
        }
        long betweenTime = DateUtils.getBetweenTime(rentDto.getStartTime(), rentDto.getEndTime());
        //加redis分布式锁
        RedisLockUtil.lock(rentDto.getCarId(), (int) betweenTime);
        car.setStatus(CarConstants.RENTED);
        carMapper.updateById(car);
        //生成出租单
        RentOrder rentOrder = new RentOrder();
        rentOrder.setId(SnowFlakeIdUtil.nextId().toString());
        BeanUtils.copyProperties(rentDto,rentOrder);
        rentOrder.setStartTime(DateUtils.stringToDate(rentDto.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
        rentOrder.setEndTime(DateUtils.stringToDate(rentDto.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
        int result =  rentOrderMapper.insert(rentOrder);
        log.info("租车成功，生成出租单成功");
        return result;
    }

    @Override
    public int returnCar(ReturnDto returnDto) {
        RentOrder rentOrder = this.getRentOrderExist(returnDto.getRentOrderId());
        Car car = this.getCarExist(rentOrder.getCarId());
        if(CarConstants.FREE.equals(car.getStatus())){
            throw RentalError.CAR_RETURN_ERROR.toException();
        }
        car.setStatus(CarConstants.FREE);
        carMapper.updateById(car);
        //存在redis锁，提前还车，删除锁
        if(stringRedisTemplate.hasKey(car.getId())){
            stringRedisTemplate.delete(car.getId());
            rentOrder.setEndTime(new Date());
            int result = rentOrderMapper.updateById(rentOrder);
            log.info("手动归还成功，出租单更新成功");
            return result;
        } else {
            throw RentalError.CAR_EXPIRED.toException();
        }

    }

    @Override
    public List<RentOrder> getRentOrderByUserId(String userId) {
        QueryWrapper<RentOrder> queryWrapper = new QueryWrapper<RentOrder>();
        queryWrapper.eq("user_id", userId);
        return rentOrderMapper.selectList(queryWrapper);
    }


    /**
     * 判断车辆是否存在
     * @param id 车辆id
     * @return 车辆对象
     */
    private Car getCarExist(String id){
        Car car = carMapper.selectById(id);
        if (car == null){
            throw RentalError.CAR_NOT_EXIST.toException();
        }
        return car;
    }

    /**
     * 判断出租单是否存在
     * @param id 出租单id
     * @return 出租单
     */
    private RentOrder getRentOrderExist(String id){
        RentOrder rentOrder = rentOrderMapper.selectById(id);
        if (rentOrder == null){
            throw RentalError.RENT_ORDER_NOT_EXIST.toException();
        }
        return rentOrder;
    }
}
