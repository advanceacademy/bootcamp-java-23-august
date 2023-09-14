package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.FileResourceRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarCategoryServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.CarServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.FileResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LoadCarRunner implements CommandLineRunner {

    private final CarServiceImpl carService;
    private final CarRepository carRepository;
    private final CarCategoryServiceImpl carCategoryService;
    private final CarCategoryRepository carCategoryRepository;
    private final FileResourceServiceImpl fileResourceService;
    private final FileResourceRepository fileResourceRepository;

    @Autowired
    public LoadCarRunner(CarServiceImpl carService, CarRepository carRepository, CarCategoryServiceImpl carCategoryService, CarCategoryRepository carCategoryRepository, FileResourceServiceImpl fileResourceService, FileResourceRepository fileResourceRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
        this.carCategoryService = carCategoryService;
        this.carCategoryRepository = carCategoryRepository;
        this.fileResourceService = fileResourceService;
        this.fileResourceRepository = fileResourceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    private void loadCarCategories() {
        Optional<List<CarCategory>> retrievedCarCategories = carCategoryRepository.findBySeats(2);
        if (retrievedCarCategories.isPresent() && retrievedCarCategories.get().size() < 3) {
            for (int i = retrievedCarCategories.get().size(); i <= 3; i++) {
                CarCategory sportCar = CarCategory.builder()
                        .carTypes(CarType.SPORT)
                        .seats(2)
                        .pricePerDay(1000.00)
                        .build();
                carCategoryRepository.save(sportCar);
            }
        }
//        List<CarCategory> allCarCategory = carCategoryService.getAllCarCategory();
//        if (!allCarCategory.isEmpty() && allCarCategory.size() < 3) {
//            for (int i = allCarCategory.size(); i <= 3; i++) {
//                CarCategory sportCar = CarCategory.builder()
//                        .carTypes(CarType.SPORT)
//                        .seats(2)
//                        .pricePerDay(1000.00)
//                        .build();
//                carCategoryService.addCategory(sportCar);
//            }
//        }
    }
}
