package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarCategoryRepository carCategoryRepository;


    @InjectMocks
    private CarServiceImpl carService;
    private Car car;
    private CarCategory carCategory;





    @BeforeEach
    public void setUp(){
        carCategory=CarCategory.builder()
                .id(1L)
                .carTypes(CarType.SEDAN)
                .seats(5)
                .pricePerDay(800.0)
                .build();
        car= Car.builder()
                .id(1L)
                .make("Audi")
                .model("A8")
                .year(2021)
                .carCategory(carCategory)
                .build();
    }
    @DisplayName("Test for added car")
    @Test
    public void testAddCar(){given(carRepository.findById(car.getId())).willReturn(Optional.empty());
        given(carRepository.save(car)).willReturn(car);
        Car addedCar=carService.addCar(car);
        assertThat(addedCar).isNotNull();
    }
    @DisplayName("Test for list all cars")
    @Test
    public void testGetAllCars(){
       CarCategory carCategory1=CarCategory.builder()
                .id(2L)
                .carTypes(CarType.SPORT)
                .seats(2)
                .pricePerDay(1000.0)
                .build();
      Car car1= Car.builder()
                .id(2L)
                .make("Ferrari")
                .model("F8")
                .year(2021)
                .carCategory(carCategory)
                .build();

        given(carRepository.findAll()).willReturn(List.of(car,car1));
        List<Car> carList=carService.getAllCars();
        assertThat(carList).isNotNull();
        assertThat(carList.size()).isEqualTo(2);
    }
    @DisplayName("Test for car get by id")
    @Test
    public void testGetCarById(){
        given(carRepository.findById(1L)).willReturn(Optional.of(car));
        Car gotCar=carService.getCarById(car.getId()).get();
        assertThat(gotCar).isNotNull();

    }
    @DisplayName("Test for updated car by id")
    @Test
    public void testUpdateCar(){
        given(carRepository.save(car)).willReturn(car);
        car.setMake("BMV");
        car.setModel("seria 5");
        car.setYear(2020);
        Car updatedCar=carService.updateCar(car);
        assertThat(updatedCar.getMake()).isEqualTo("BMV");
        assertThat(updatedCar.getModel()).isEqualTo("seria 5");
        assertThat(updatedCar.getYear()).isEqualTo(2020);
    }
    @DisplayName("Test for deleted car by id")
    @Test
    public void testDeleteCar(){
        Long carId=1L;
        willDoNothing().given(carRepository).deleteById(carId);
        carService.deleteById(carId);
       verify(carRepository, times(1)).deleteById(carId);
    }

}
