package com.advanceacademy.moonlighthotel.repository.car;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarTransferRepository extends JpaRepository<CarTransfer, Long> {

    Optional<List<CarTransfer>> findByUserId(Long userId);

    Optional<List<CarTransfer>> findByCarId(Long carId);

    Optional<List<CarTransfer>> findByDate(LocalDate date);

    Optional<List<CarTransfer>> findByPaymentStatus(PaymentStatus paymentStatus);

}
