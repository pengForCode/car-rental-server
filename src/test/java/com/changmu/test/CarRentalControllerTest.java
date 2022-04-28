package com.changmu.test;

import com.changmu.controller.CarRentalController;
import com.changmu.db.dto.CarDto;
import com.changmu.db.dto.RentDto;
import com.changmu.db.dto.ReturnDto;
import com.changmu.db.entity.Car;
import com.changmu.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28 21:17
 * @description 租车控制器测试类
 */

@SpringBootTest
public class CarRentalControllerTest {

    private MockMvc mvc;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarRentalController carRentalController;

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(carRentalController).build();
    }

    @Test
    void getAllCars() throws Exception {
        String uri = "/car/all";
        String responseJson = mvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getRentOrders() throws Exception {
        String uri = "/car/rentOrders";
        String userId = "1519572275039440878";
        String responseJson = mvc
                .perform(MockMvcRequestBuilders.get(uri).queryParam("userId",userId))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        verify(carService, times(1)).getRentOrderByUserId(userId);
    }

    @Test
    void addCar() throws Exception {
        String uri = "/car/add";
        CarDto carDto = CarDto.builder().carCode("Y99999").description("牛皮车").build();
        String requestBody = objectMapper.writeValueAsString(carDto);
        mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(carService, times(1)).addCar(any(CarDto.class));
    }

    @Test
    void updateCar() throws Exception {
        String uri = "/car/update";
        CarDto carDto = CarDto.builder().id("1519644587939991552").carCode("Y99999").description("牛皮车").build();
        String requestBody = objectMapper.writeValueAsString(carDto);
        mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(carService, times(1)).updateCar(any(CarDto.class));
    }
    @Test
    void removeCar() throws Exception {
        String uri = "/car/remove";
        CarDto carDto = CarDto.builder().id("1519644587939991552").build();
        String requestBody = objectMapper.writeValueAsString(carDto);
        mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(carService, times(1)).removeCar(any(CarDto.class));
    }

    @Test
    void rentCar() throws Exception {
        String uri = "/car/rent";
        RentDto rentDto = RentDto.builder().userId("1519572275039440878")
                .carId("1519644587939991552").startTime("2022-04-28 18:15:23").endTime("2022-04-29 18:15:23")
                .build();
        String requestBody = objectMapper.writeValueAsString(rentDto);
        mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(carService, times(1)).rentCar(any(RentDto.class));
    }

    @Test
    void returnCar() throws Exception {
        String uri = "/car/return";
        ReturnDto returnDto = ReturnDto.builder().rentOrderId("1519649247111286784").build();
        String requestBody = objectMapper.writeValueAsString(returnDto);
        mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(carService, times(1)).returnCar(any(ReturnDto.class));
    }
}
