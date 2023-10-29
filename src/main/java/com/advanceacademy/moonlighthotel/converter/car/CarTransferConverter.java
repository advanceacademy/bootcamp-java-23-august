package com.advanceacademy.moonlighthotel.converter.car;

import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarTransferRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CarTransferConverter {

    private final CarTransferRepository carTransferRepository;

    private final CarService carService;

    private final UserRepository userRepository;

    private String extractUserEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }

    private User findUserByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new ResourceNotFoundException("User with this email not found"));
    }

    private List<CarTransfer> getCarTransfersByCarAndDate(Car car, LocalDate date) {
        return carTransferRepository.findByCarAndDate(car, date);
    }

    public CarTransfer toCarTransfer(CarTransferRequestDto carTransferRequestDto){
        Car foundCar = carService.getCarById(carTransferRequestDto.getCarId()).orElseThrow();
        String userEmail = extractUserEmailFromToken();
        User user = findUserByEmail(userEmail);

        LocalDate requestedDate = carTransferRequestDto.getDate();
        if (!requestedDate.isBefore(LocalDate.now())){

            List<CarTransfer> existingCarTransfers = getCarTransfersByCarAndDate(foundCar, requestedDate);

            if (!existingCarTransfers.isEmpty()) {
                throw new ResourceNotFoundException("Car is already booked for the requested date");
            }


            return CarTransfer.builder()
                    .car(foundCar)
                    .date(carTransferRequestDto.getDate())
                    .user(user)
                    .price(foundCar.getCarCategory().getPricePerDay())
                    .paymentStatus(PaymentStatus.PAID)
                    .build();

        } else {
            throw new DateTimeException("Date must be present or future");
        }


    }

    public CarTransferResponseDto toResponse(CarTransfer savedCarTransfer){

        return new CarTransferResponseDto(
                savedCarTransfer.getId(),
                savedCarTransfer.getCar(),
                savedCarTransfer.getUser(),
                savedCarTransfer.getDate(),
                savedCarTransfer.getPrice());
    }
}
