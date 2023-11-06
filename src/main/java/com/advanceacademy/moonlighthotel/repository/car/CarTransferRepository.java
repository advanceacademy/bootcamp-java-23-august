package com.advanceacademy.moonlighthotel.repository.car;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarTransferRepository extends JpaRepository<CarTransfer, Long> {

    Optional<List<CarTransfer>> findByUserId(Long userId);

    Optional<List<CarTransfer>> findByCarId(Long carId);


    List<CarTransfer> findByCarAndDate(Car car, LocalDate date);


    Optional<List<CarTransfer>> findByDate(LocalDate date);

    Optional<List<CarTransfer>> findByPaymentStatus(PaymentStatus paymentStatus);

    Optional<List<CarTransfer>> findByUser(User user);

}
