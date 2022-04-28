package com.changmu.controller;

import com.changmu.db.dto.CarDto;
import com.changmu.db.dto.RentDto;
import com.changmu.db.dto.ReturnDto;
import com.changmu.db.entity.Car;
import com.changmu.db.entity.RentOrder;
import com.changmu.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * @author chang mu
 * @version 1.0
 * @date 2022/4/27 22:15
 * @description
 */
@RestController
@RequestMapping("/car" )
@Slf4j
public class CarRentalController {

    @Autowired
    private CarService carService;

    /**
     * 查询所有车辆
     * @return 车辆列表
     */
    @GetMapping("/all")
    private ResponseEntity<List<Car>> getAllCars(){
        List<Car> allCars = carService.getAllCars();
        return ResponseEntity.ok(allCars);
    }

    /**
     * 添加车辆
     * @param carDto 车辆信息
     * @return 添加结果
     */
    @PostMapping("/add")
    private ResponseEntity<Integer> addCar(@RequestBody @Valid CarDto carDto){
        return ResponseEntity.ok(carService.addCar(carDto));
    }

    /**
     * 修改车辆
     * @param carDto 车辆信息
     * @return 修改结果
     */
    @PostMapping("/update")
    private ResponseEntity<Integer> updateCars(@RequestBody @Valid CarDto carDto){
        return ResponseEntity.ok(carService.updateCar(carDto));
    }

    /**
     * 删除车辆
     * @param carDto 车辆信息
     * @return 删除结果
     */
    @PostMapping("/remove")
    private ResponseEntity<Integer> removeCar(@RequestBody @Valid CarDto carDto){
        return ResponseEntity.ok(carService.removeCar(carDto));
    }

    /**
     * 租赁车辆
     * @param rentDto 租赁信息
     * @return 租赁结果
     */
    @PostMapping("/rent")
    private ResponseEntity<Integer> rentCar(@RequestBody @Valid RentDto rentDto) throws ParseException {
        return ResponseEntity.ok(carService.rentCar(rentDto));
    }

    /**
     * 归还车辆
     * @param returnDto 归还信息
     * @return 删除结果
     */
    @PostMapping("/return")
    private ResponseEntity<Integer> returnCar(@RequestBody @Valid ReturnDto returnDto){
        return ResponseEntity.ok(carService.returnCar(returnDto));
    }

    /**
     * 根据用户id查询出租单列表
     * @param userId 用户id
     * @return 出租单列表
     */
    @GetMapping("/rentOrders")
    public ResponseEntity<List<RentOrder>> getRentOrderByUserId(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(carService.getRentOrderByUserId(userId));
    }

}
