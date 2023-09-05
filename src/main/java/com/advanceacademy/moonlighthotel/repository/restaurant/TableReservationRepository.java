package com.advanceacademy.moonlighthotel.repository.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation,Long> {

    Optional<TableReservation> findById(Long id);

    void deleteById(Long id);

    List<TableReservation> findByUserId(Long userId);

    List<TableReservation> findByTableId(Long tableId);

    List<TableReservation> findByDate(LocalDate date);

}
