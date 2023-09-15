package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.FileResourceRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarCategoryServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.CarServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.FileResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoadCarRunner implements CommandLineRunner {

    @Autowired
    CarServiceImpl carService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarCategoryServiceImpl carCategoryService;

    @Autowired
    CarCategoryRepository carCategoryRepository;

    @Autowired
    FileResourceServiceImpl fileResourceService;

    @Autowired
    FileResourceRepository fileResourceRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCarCategories();
        loadCarImages();
        loadCars();
    }

//    private void loadCarCategories() {
//        Optional<List<CarCategory>> retrievedSportCarCategories = carCategoryRepository.findBySeats(2);
//
//        if (retrievedSportCarCategories.isEmpty() || retrievedSportCarCategories.get().size() < 3) {
//            for (int i = (retrievedSportCarCategories.map(List::size).orElse(0)); i < 3; i++) {
//                CarCategory sportCar = CarCategory.builder()
//                        .seats(2)
//                        .carTypes(CarType.SPORT)
//                        .pricePerDay(1000.00)
//                        .build();
//                carCategoryService.addCategory(sportCar);
//            }
//        } else {
//            throw new DuplicateRecordException("CarCategories with type SPORT already exist.");
//        }
//
//        Optional<List<CarCategory>> retrievedSedanCarCategories = carCategoryRepository.findBySeats(5);
//
//        if (retrievedSedanCarCategories.isEmpty() || retrievedSedanCarCategories.get().size() < 4) {
//            for (int i = (retrievedSedanCarCategories.map(List::size).orElse(0)); i < 4; i++) {
//                CarCategory sedanCar = CarCategory.builder()
//                        .seats(5)
//                        .carTypes(CarType.SEDAN)
//                        .pricePerDay(800.00)
//                        .build();
//                carCategoryService.addCategory(sedanCar);
//            }
//        } else {
//            throw new DuplicateRecordException("CarCategories with type SEDAN already exist.");
//        }
//
//        Optional<List<CarCategory>> retrievedVanCarCategories = carCategoryRepository.findBySeats(8);
//
//        if (retrievedVanCarCategories.isEmpty() || retrievedVanCarCategories.get().size() < 4) {
//            for (int i = (retrievedVanCarCategories.map(List::size).orElse(0)); i < 4; i++) {
//                CarCategory vanCar = CarCategory.builder()
//                        .seats(8)
//                        .carTypes(CarType.VAN)
//                        .pricePerDay(600.00)
//                        .build();
//                carCategoryService.addCategory(vanCar);
//            }
//        } else {
//            throw new DuplicateRecordException("CarCategories with type VAN already exist.");
//        }
//    }

    private void loadCarCategories(){
        Optional<CarCategory> categoryByTwoSeats = carCategoryRepository.findBySeats(2);
        if (categoryByTwoSeats.isEmpty()){
            CarCategory sportCar = CarCategory.builder()
                    .seats(2)
                    .carTypes(CarType.SPORT)
                    .pricePerDay(1000.00)
                    .build();
            carCategoryService.addCategory(sportCar);
        }else {
            throw new DuplicateRecordException("CarCategories with type SPORT already exist.");
        }

        Optional<CarCategory> categoryByFiveSeats = carCategoryRepository.findBySeats(5);
        if (categoryByFiveSeats.isEmpty()){
            CarCategory sedanCar = CarCategory.builder()
                    .seats(5)
                    .carTypes(CarType.SEDAN)
                    .pricePerDay(800.00)
                    .build();
            carCategoryService.addCategory(sedanCar);
        }else {
            throw new DuplicateRecordException("CarCategories with type SEDEN already exist.");
        }

        Optional<CarCategory> categoryByEightSeats = carCategoryRepository.findBySeats(8);
        if (categoryByEightSeats.isEmpty()){
            CarCategory vanCar = CarCategory.builder()
                    .seats(8)
                    .carTypes(CarType.VAN)
                    .pricePerDay(600.00)
                    .build();
            carCategoryService.addCategory(vanCar);
        }else {
            throw new DuplicateRecordException("CarCategories with type VAN already exist.");
        }
    }

    private void loadCarImages(){

    }

    private void loadCars(){
        List<Car> retrievedCars = carService.getAllCars();
        List<Car> allOwnedCars = new ArrayList<>();
        Optional<CarCategory> sportCategory = carCategoryRepository.findBySeats(2);
        Optional<CarCategory> sedanCategory = carCategoryRepository.findBySeats(5);
        Optional<CarCategory> vanCategory = carCategoryRepository.findBySeats(8);
        if (sportCategory.isPresent()){
            Car audiSportCar = Car.builder()
                    .make("Audi")
                    .model("R8")
                    .year(2021)
                    .carCategory(sportCategory.get())
//             TODO       .fileResources();
                    .build();

            Car lamborghiniSportCar = Car.builder()
                    .make("Lamborghini")
                    .model("Urus")
                    .year(2020)
                    .carCategory(sportCategory.get())
//             TODO       .fileResources();
                    .build();

            Car ferrariSportCar = Car.builder()
                    .make("Ferrari")
                    .model("F8")
                    .year(2021)
                    .carCategory(sportCategory.get())
//             TODO       .fileResources();
                    .build();

            allOwnedCars.add(audiSportCar);
            allOwnedCars.add(lamborghiniSportCar);
            allOwnedCars.add(ferrariSportCar);

        }

        if (sedanCategory.isPresent()){
            Car mercedesSedantCar = Car.builder()
                    .make("Mercedes")
                    .model("S class")
                    .year(2021)
                    .carCategory(sedanCategory.get())
//             TODO       .fileResources();
                    .build();

            Car audiSedantCar = Car.builder()
                    .make("Audi")
                    .model("A8")
                    .year(2021)
                    .carCategory(sedanCategory.get())
//             TODO       .fileResources();
                    .build();

            Car bmwSedantCar = Car.builder()
                    .make("BMW")
                    .model("5 serial")
                    .year(2020)
                    .carCategory(sedanCategory.get())
//             TODO       .fileResources();
                    .build();

            Car vwSedantCar = Car.builder()
                    .make("VW")
                    .model("Passat B9")
                    .year(2021)
                    .carCategory(sedanCategory.get())
//             TODO       .fileResources();
                    .build();

            allOwnedCars.add(mercedesSedantCar);
            allOwnedCars.add(audiSedantCar);
            allOwnedCars.add(bmwSedantCar);
            allOwnedCars.add(vwSedantCar);

        }

        if (vanCategory.isPresent()){
            Car mercedesVanCar = Car.builder()
                    .make("Mercedes")
                    .model("V class")
                    .year(2021)
                    .carCategory(vanCategory.get())
//             TODO       .fileResources();
                    .build();

            Car vwVanCar = Car.builder()
                    .make("VW")
                    .model("R line")
                    .year(2020)
                    .carCategory(vanCategory.get())
//             TODO       .fileResources();
                    .build();

            Car bmwVanCar = Car.builder()
                    .make("BMW")
                    .model("M4")
                    .year(2020)
                    .carCategory(vanCategory.get())
//             TODO       .fileResources();
                    .build();

            Car hyundaiVanCar = Car.builder()
                    .make("Hyundai")
                    .model("H-1")
                    .year(2020)
                    .carCategory(vanCategory.get())
//             TODO       .fileResources();
                    .build();

            allOwnedCars.add(mercedesVanCar);
            allOwnedCars.add(vwVanCar);
            allOwnedCars.add(bmwVanCar);
            allOwnedCars.add(hyundaiVanCar);
        }
        for (Car ownedCar : allOwnedCars) {
            if (!retrievedCars.contains(ownedCar)){
                carService.addCar(ownedCar);
            }
        }
    }
}
