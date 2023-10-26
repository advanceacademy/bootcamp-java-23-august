package com.advanceacademy.moonlighthotel.controller.car;

import com.advanceacademy.moonlighthotel.converter.car.CarConverter;
import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class CarController {

    private final CarService carService;
    private final CarConverter carConverter;

    @Autowired
    public CarController(CarService carService, CarConverter converter) {
        this.carService = carService;
        this.carConverter = converter;
    }

    @GetMapping("/auth/cars")
    @Operation(
            description = "Get all cars",
            summary = "Retrieve All Cars",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found",
                            responseCode = "404"
                    )
            },
            operationId = "getAllCars",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/auth/cars/by-category/{categoryId}")
    @Operation(
            description = "Get cars by category",
            summary = "Retrieve Cars by Category",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found for the specified category",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "categoryId",
                            description = "The ID of the car category to filter cars by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "getCarsByCategory",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByCategory(@PathVariable Long categoryId) {
        List<Car> cars = carService.getCarsByCategory(categoryId);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/auth/cars/by-year/{year}")
    @Operation(
            description = "Get cars by year",
            summary = "Retrieve Cars by Year",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found for the specified year",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "year",
                            description = "The year to filter cars by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getCarsByYear",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByYear(@PathVariable Integer year) {
        List<Car> cars = carService.getCarsByYear(year);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/auth/cars/by-model/{model}")
    @Operation(
            description = "Get cars by model",
            summary = "Retrieve Cars by Model",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found for the specified model",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "model",
                            description = "The car model to filter cars by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getCarsByModel",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByModel(@PathVariable String model) {
        List<Car> cars = carService.getCarsByModel(model);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/auth/cars/by-make/{make}")
    @Operation(
            description = "Get cars by make",
            summary = "Retrieve Cars by Make",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found for the specified make",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "make",
                            description = "The car make to filter cars by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getCarsByMake",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByMake(@PathVariable String make) {
        List<Car> cars = carService.getCarsByMake(make);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping("/auth/cars/{carId}")
    @Operation(
            description = "Get a car by ID",
            summary = "Retrieve Car by ID",
            responses = {
                    @ApiResponse(
                            description = "Car retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarBaseResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No car found for the specified ID",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "carId",
                            description = "The ID of the car to retrieve",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "getCarById",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<CarBaseResponseDto> getCarById(@PathVariable Long carId) {
        Optional<Car> car = carService.getCarById(carId);
        if (car.isPresent()) {
            CarBaseResponseDto carBaseResponseDto = carConverter.responseDto(car.get());
            return ResponseEntity.ok(carBaseResponseDto);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/auth/cars/by-type/{carType}")
    @Operation(
            description = "Get cars by type",
            summary = "Retrieve Cars by Type",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found for the specified type",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "carType",
                            description = "The car type to filter cars by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getCarsByType",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByType(@PathVariable String carType) {
        List<Car> cars = carService.getCarsByType(carType);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/auth/cars/by-seats/{seats}")
    @Operation(
            description = "Get cars by seats",
            summary = "Retrieve Cars by Number of Seats",
            responses = {
                    @ApiResponse(
                            description = "Cars retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CarBaseResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No cars found for the specified number of seats",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "seats",
                            description = "The number of seats to filter cars by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getCarsBySeats",
            tags = {"Car"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<CarBaseResponseDto>> getCarsBySeats(@PathVariable Integer seats) {
        List<Car> cars = carService.getCarsBySeats(seats);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping("/admin/car-reservation/get-all")
    public ResponseEntity<List<CarBaseResponseDto>> getAllCarReservations(){
        List<Car> allCarReservations = carService.getAllCars();

        if (allCarReservations.isEmpty()){
            return ResponseEntity.notFound().build();

        }

        List<CarBaseResponseDto> carBaseResponseDtoList = allCarReservations
                .stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtoList);

    }

    public void validateParameters(LocalDate date, Integer seats){
        if(date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Date must be a current or future date.");
        }
        if(seats < 1){
            throw new IllegalArgumentException("Required seats must be at least 1.");
        }
    }
    @GetMapping(path = "user/cars/by-date-and-seats")
    public ResponseEntity<List<CarBaseResponseDto>> getAvailableCarsByDateAndSeats(@RequestParam("transfer_date") LocalDate date, @RequestParam ("seats_number") Integer seats){
        validateParameters(date, seats);
        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getAvailableCarsByDateAndSeats(date, seats));

    }

    @GetMapping(path = "user/cars/by-date-seats-category-model")
    public ResponseEntity<List<CarBaseResponseDto>> getAvailableCarByDateSeatsCategoryModel(@RequestParam ("transfer_date") LocalDate date,
                                                                                            @RequestParam ("seats_number") Integer seats,
                                                                                            @RequestParam ("car_category") Long categoryId,
                                                                                            @RequestParam ("car_model") String model)

    {
        validateParameters(date, seats);
        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getAvailableCarsByDateSeatsCategoryModel(date, seats, categoryId, model));
    }
}



