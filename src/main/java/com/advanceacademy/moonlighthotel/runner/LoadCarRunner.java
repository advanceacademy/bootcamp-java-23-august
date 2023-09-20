package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.FileResourceRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarCategoryServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.CarServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.FileResourceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
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

    private void loadCarCategories() {
        CarCategory sportCar = CarCategory.builder()
                .seats(2)
                .carTypes(CarType.SPORT)
                .pricePerDay(1000.00)
                .build();

        Optional<CarCategory> categoryByTwoSeats = carCategoryRepository.findBySeats(2);
        if (categoryByTwoSeats.isEmpty()) {
            carCategoryRepository.save(sportCar);
        }

        CarCategory sedanCar = CarCategory.builder()
                .seats(5)
                .carTypes(CarType.SEDAN)
                .pricePerDay(800.00)
                .build();

        Optional<CarCategory> categoryByFiveSeats = carCategoryRepository.findBySeats(5);
        if (categoryByFiveSeats.isEmpty()) {
            carCategoryRepository.save(sedanCar);
        }

        CarCategory vanCar = CarCategory.builder()
                .seats(8)
                .carTypes(CarType.VAN)
                .pricePerDay(600.00)
                .build();

        Optional<CarCategory> categoryByEightSeats = carCategoryRepository.findBySeats(8);
        if (categoryByEightSeats.isEmpty()) {
            carCategoryRepository.save(vanCar);
        }
    }

    @Transactional
    private void loadCarImages() throws IOException {

        String[] sportCarImageFiles = {
                "ferrari1.jpg",
                "ferrari2.jpg",
                "ferrari3.jpg",
                "audi1.png",
                "audi2.png",
                "audi3.png",
                "lamborghini1.png",
                "lamborghini2.png",
                "lamborghini3.png",
        };
        String[] vanCarImageFiles = {
                "bmwvan1.jpg",
                "bmwvan2.jpg",
                "bmwvan3.jpg",
                "hyundai1.jpg",
                "hyundai2.jpg",
                "hyundai3.jpg",
                "mercedesv1.jpg",
                "mercedesv2.jpg",
                "mercedesv3.jpg",
                "vwrline1.jpg",
                "vwrline2.jpg",
                "vwrline3.jpg"
        };
        String[] sedanCarImageFiles = {
                "audia81.png",
                "audia82.png",
                "audia83.png",
                "bmw51.png",
                "bmw52.png",
                "bmw53.png",
                "mercedes1.png",
                "mercedes2.png",
                "mercedes3.png",
                "passatb91.jpg",
                "passatb91.jpg",
                "passatb91.jpg"
        };
        loadImagesForCategory("sportcar", sportCarImageFiles);
        loadImagesForCategory("sedancar", sedanCarImageFiles);
        loadImagesForCategory("vancar", vanCarImageFiles);


    }

    private void loadImagesForCategory(String carCategory, String[] imageFiles) throws IOException {
        for (String imageFile : imageFiles) {
            String uniqueImageName = imageFile + "_" + UUID.randomUUID();
            FileResource carImage = FileResource.builder()
                    .value(fileResourceService.readImageFromFileOrSource(carCategory, imageFile))
                    .imageName(uniqueImageName)
                    .build();
            fileResourceRepository.save(carImage);
        }
    }

    @Transactional
    private void loadCars() {
        List<Car> retrievedCars = carService.getAllCars();
        List<Car> allOwnedCars = new ArrayList<>();
        Optional<CarCategory> sportCategory = carCategoryRepository.findBySeats(2);
        Optional<CarCategory> sedanCategory = carCategoryRepository.findBySeats(5);
        Optional<CarCategory> vanCategory = carCategoryRepository.findBySeats(8);
        if (sportCategory.isPresent()) {
            Car audiSportCar = Car.builder()
                    .make("Audi")
                    .model("R8")
                    .year(2021)
                    .carCategory(sportCategory.get())
                    .fileResources(fileResourceService.findByName("audi"))
                    .build();
            carRepository.save(audiSportCar);


            Car lamborghiniSportCar = Car.builder()
                    .make("Lamborghini")
                    .model("Urus")
                    .year(2020)
                    .carCategory(sportCategory.get())
                    .fileResources(fileResourceService.findByName("Lamborghini"))
                    .build();
            carRepository.save(lamborghiniSportCar);

            Car ferrariSportCar = Car.builder()
                    .make("Ferrari")
                    .model("F8")
                    .year(2021)
                    .carCategory(sportCategory.get())
                    .fileResources(fileResourceService.findByName("Ferrari"))
                    .build();
            carRepository.save(ferrariSportCar);

            allOwnedCars.add(audiSportCar);
            allOwnedCars.add(lamborghiniSportCar);
            allOwnedCars.add(ferrariSportCar);

        }
        if (sedanCategory.isPresent()) {

            Car mercedesSedantCar = Car.builder()
                    .make("Mercedes")
                    .model("S class")
                    .year(2021)
                    .carCategory(sedanCategory.get())
                    .fileResources(fileResourceService.findByName("Mercedes"))
                    .build();
            carRepository.save(mercedesSedantCar);

            Car audiSedantCar = Car.builder()
                    .make("Audi")
                    .model("A8")
                    .year(2021)
                    .carCategory(sedanCategory.get())
                    .fileResources(fileResourceService.findByName("Audi"))
                    .build();
            carRepository.save(audiSedantCar);

            Car bmwSedantCar = Car.builder()
                    .make("BMW")
                    .model("5 serial")
                    .year(2020)
                    .carCategory(sedanCategory.get())
                    .fileResources(fileResourceService.findByName("BMW"))
                    .build();
            carRepository.save(bmwSedantCar);

            Car vwSedantCar = Car.builder()
                    .make("VW")
                    .model("Passat B9")
                    .year(2021)
                    .carCategory(sedanCategory.get())
                    .fileResources(fileResourceService.findByName("VW"))
                    .build();
            carRepository.save(vwSedantCar);

            allOwnedCars.add(mercedesSedantCar);
            allOwnedCars.add(audiSedantCar);
            allOwnedCars.add(bmwSedantCar);
            allOwnedCars.add(vwSedantCar);

        }

        if (vanCategory.isPresent()) {
            Car mercedesVanCar = Car.builder()
                    .make("Mercedes")
                    .model("V class")
                    .year(2021)
                    .carCategory(vanCategory.get())
                    .fileResources(fileResourceService.findByName("Mercedes"))
                    .build();
            carRepository.save(mercedesVanCar);

            Car vwVanCar = Car.builder()
                    .make("VW")
                    .model("R line")
                    .year(2020)
                    .carCategory(vanCategory.get())
                    .fileResources(fileResourceService.findByName("VW"))
                    .build();
            carRepository.save(vwVanCar);

            Car bmwVanCar = Car.builder()
                    .make("BMW")
                    .model("M4")
                    .year(2020)
                    .carCategory(vanCategory.get())
                    .fileResources(fileResourceService.findByName("BMW"))
                    .build();
            carRepository.save(bmwVanCar);

            Car hyundaiVanCar = Car.builder()
                    .make("Hyundai")
                    .model("H-1")
                    .year(2020)
                    .carCategory(vanCategory.get())
                    .fileResources(fileResourceService.findByName("Hyundai"))
                    .build();
            carRepository.save(hyundaiVanCar);

            allOwnedCars.add(mercedesVanCar);
            allOwnedCars.add(vwVanCar);
            allOwnedCars.add(bmwVanCar);
            allOwnedCars.add(hyundaiVanCar);
        }
    }
}
