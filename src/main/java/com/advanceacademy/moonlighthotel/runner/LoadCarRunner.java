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
        loadCars();
        setUpCarToImages();
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

    private void loadCars() throws IOException {

        Optional<CarCategory> sportCategory = carCategoryRepository.findBySeats(2);
        Optional<CarCategory> sedanCategory = carCategoryRepository.findBySeats(5);
        Optional<CarCategory> vanCategory = carCategoryRepository.findBySeats(8);
        List<Car> allRetrievedCars = carService.getAllCars();

        if (sportCategory.isPresent()) {
            Car ferrariSportCar = Car.builder()
                    .make("Ferrari")
                    .model("F8")
                    .year(2021)
                    .plateNumber("B1245HP")
                    .carCategory(sportCategory.get())
                    .fileResources(loadImagesToCar("sportcar", "ferrari"))
                    .build();

            if (!isCarExists(ferrariSportCar, allRetrievedCars)) {
                carRepository.save(ferrariSportCar);
            }

            Car lamborghiniSportCar = Car.builder()
                    .make("Lamborghini")
                    .model("Urus")
                    .year(2020)
                    .plateNumber("B1112OM")
                    .carCategory(sportCategory.get())
                    .fileResources(loadImagesToCar("sportcar", "Lamborghini"))
                    .build();

            if (!isCarExists(lamborghiniSportCar, allRetrievedCars)) {
                carRepository.save(lamborghiniSportCar);
            }

            Car audiSportCar = Car.builder()
                    .make("Audi")
                    .model("R8")
                    .year(2021)
                    .plateNumber("B1414TP")
                    .carCategory(sportCategory.get())
                    .fileResources(loadImagesToCar("sportcar", "audi"))
                    .build();

            if (!isCarExists(audiSportCar, allRetrievedCars)) {
                carRepository.save(audiSportCar);
            }

        }
        if (sedanCategory.isPresent()) {
            Car mercedesSedantCar = Car.builder()
                    .make("Mercedes")
                    .model("S class")
                    .year(2021)
                    .plateNumber("B1290KP")
                    .carCategory(sedanCategory.get())
                    .fileResources(loadImagesToCar("sedancar", "Mercedes"))
                    .build();

            if (!isCarExists(mercedesSedantCar, allRetrievedCars)) {
                carRepository.save(mercedesSedantCar);
            }

            Car audiSedantCar = Car.builder()
                    .make("Audi")
                    .model("A8")
                    .year(2021)
                    .plateNumber("B4567OB")
                    .carCategory(sedanCategory.get())
                    .fileResources(loadImagesToCar("sedancar", "Audi"))
                    .build();

            if (!isCarExists(audiSedantCar, allRetrievedCars)) {
                carRepository.save(audiSedantCar);
            }

            Car bmwSedantCar = Car.builder()
                    .make("BMW")
                    .model("5 serial")
                    .year(2020)
                    .plateNumber("B9911AA")
                    .carCategory(sedanCategory.get())
                    .fileResources(loadImagesToCar("sedancar", "BMW"))
                    .build();

            if (!isCarExists(bmwSedantCar, allRetrievedCars)) {
                carRepository.save(bmwSedantCar);
            }

            Car vwSedantCar = Car.builder()
                    .make("VW")
                    .model("Passat B9")
                    .year(2021)
                    .plateNumber("B7772HP")
                    .carCategory(sedanCategory.get())
                    .fileResources(loadImagesToCar("sedancar", "VW"))
                    .build();

            if (!isCarExists(vwSedantCar, allRetrievedCars)) {
                carRepository.save(vwSedantCar);
            }

        }
        if (vanCategory.isPresent()) {
            Car mercedesVanCar = Car.builder()
                    .make("Mercedes")
                    .model("V class")
                    .year(2021)
                    .plateNumber("B6650TA")
                    .carCategory(vanCategory.get())
                    .fileResources(loadImagesToCar("vancar", "Mercedes"))
                    .build();

            if (!isCarExists(mercedesVanCar, allRetrievedCars)) {
                carRepository.save(mercedesVanCar);
            }

            Car vwVanCar = Car.builder()
                    .make("VW")
                    .model("R line")
                    .year(2020)
                    .plateNumber("B9781HA")
                    .carCategory(vanCategory.get())
                    .fileResources(loadImagesToCar("vancar", "VW"))
                    .build();

            if (!isCarExists(vwVanCar, allRetrievedCars)) {
                carRepository.save(vwVanCar);
            }

            Car bmwVanCar = Car.builder()
                    .make("BMW")
                    .model("M4")
                    .year(2020)
                    .plateNumber("B0990AP")
                    .carCategory(vanCategory.get())
                    .fileResources(loadImagesToCar("vancar", "BMW"))
                    .build();

            if (!isCarExists(bmwVanCar, allRetrievedCars)) {
                carRepository.save(bmwVanCar);
            }

            Car hyundaiVanCar = Car.builder()
                    .make("Hyundai")
                    .model("H-1")
                    .year(2020)
                    .plateNumber("B3296TK")
                    .carCategory(vanCategory.get())
                    .fileResources(loadImagesToCar("vancar", "Hyundai"))
                    .build();

            if (!isCarExists(hyundaiVanCar, allRetrievedCars)) {
                carRepository.save(hyundaiVanCar);
            }

        }
    }

    private List<FileResource> loadImagesToCar(String carCategory, String filterPrefix) throws IOException {
        String[] imageFiles;

        // Determine which array to use based on the car category
        if ("sportcar".equals(carCategory)) {
            imageFiles = new String[]{
                    "audi1.png", "audi2.png", "audi3.png",
                    "ferrari1.jpg", "ferrari2.jpg", "ferrari3.jpg",
                    "lamborghini1.png", "lamborghini2.png", "lamborghini3.png"
            };
        } else if ("sedancar".equals(carCategory)) {
            imageFiles = new String[]{
                    "audi1.png", "audi2.png", "audi3.png",
                    "bmw1.png", "bmw2.png", "bmw3.png",
                    "mercedes1.png", "mercedes2.png", "mercedes3.png",
                    "vw1.jpg", "vw2.jpg", "vw3.jpg"
            };
        } else if ("vancar".equals(carCategory)) {
            imageFiles = new String[]{
                    "bmw1.jpg", "bmw2.jpg", "bmw3.jpg",
                    "hyundai1.jpg", "hyundai2.jpg", "hyundai3.jpg",
                    "mercedes1.jpg", "mercedes2.jpg", "mercedes3.jpg",
                    "vw1.jpg", "vw2.jpg", "vw3.jpg"
            };
        } else {
            // Handle unknown car category or provide a default array
            throw new IllegalArgumentException("Unknown car category: " + carCategory);
        }

        List<FileResource> carImages = new ArrayList<>();

        for (String imageFile : imageFiles) {
            if (imageFile.toLowerCase().startsWith(filterPrefix.toLowerCase())) {
                String uniqueImageName = imageFile + "_" + UUID.randomUUID();

                // Check if a FileResource with the same value property already exists
                byte[] imageValue = fileResourceService.readImageFromFileOrSource(carCategory, imageFile);
                Optional<FileResource> existingFileResource = fileResourceRepository.findByValue(imageValue);

                if (existingFileResource.isPresent()) {
                    // If it exists, reuse the existing FileResource
                    carImages.add(existingFileResource.get());
                } else {
                    // Create and save a new FileResource
                    FileResource carImage = FileResource.builder()
                            .value(imageValue)
                            .imageName(uniqueImageName)
                            .build();
                    fileResourceRepository.save(carImage);
                    carImages.add(carImage);
                }
            }
        }
        return carImages;
    }


    private boolean isCarExists(Car car, List<Car> cars) {
        for (Car currentCar : cars) {
            if (currentCar.getPlateNumber().equals(car.getPlateNumber())) {
                // The car exists, return true
                return true;
            }
        }
        // The car does not exist, return false
        return false;
    }

    private void setUpCarToImages() {
        long[] resourceIds = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L};
        long[] carIds = {1L, 1L, 1L, 2L, 2L, 2L, 3L, 3L, 3L, 4L, 4L, 4L, 5L, 5L, 5L, 6L, 6L, 6L, 7L, 7L, 7L, 8L, 8L, 8L, 9L, 9L, 9L, 10L, 10L, 10L, 11L, 11L, 11L};

        for (int i = 0; i < resourceIds.length; i++) {
            Optional<FileResource> foundResource = fileResourceRepository.findById(resourceIds[i]);
            Optional<Car> foundCar = carRepository.findById(carIds[i]);

            if (foundResource.isPresent() && foundCar.isPresent()) {
                FileResource update = foundResource.get();
                update.setCar(foundCar.get());
                fileResourceService.updateFileResource(resourceIds[i], update);
                System.out.println(update);
            }
        }
    }
}

