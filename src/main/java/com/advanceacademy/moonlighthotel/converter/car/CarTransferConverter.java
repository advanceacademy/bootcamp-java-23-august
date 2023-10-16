package com.advanceacademy.moonlighthotel.converter.car;

import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.security.jwt.AuthTokenFilter;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import com.advanceacademy.moonlighthotel.service.user.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CarTransferConverter {

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
    public CarTransfer toCarTransfer(CarTransferRequestDto carTransferRequestDto){
        Car foundCar = carService.getCarById(carTransferRequestDto.getCarId()).orElseThrow();
        String userEmail = extractUserEmailFromToken();
        User user = findUserByEmail(userEmail);

            return CarTransfer.builder()
                    .car(foundCar)
                    .date(carTransferRequestDto.getDate())
                    .user(user)
                    .price(foundCar.getCarCategory().getPricePerDay())
                    .paymentStatus(PaymentStatus.PAID)
                    .build();

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
