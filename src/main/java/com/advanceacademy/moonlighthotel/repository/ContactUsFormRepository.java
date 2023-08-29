package com.advanceacademy.moonlighthotel.repository;

import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactUsFormRepository extends JpaRepository<ContactUsForm, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM ContactUsForm c " +
            "WHERE (:userEmail IS NULL OR c.userEmail = :userEmail) " +
            "AND (:userName IS NULL OR c.userName = :userName) " +
            "AND (:userPhone IS NULL OR c.userPhone = :userPhone) " +
            "AND (:userMessage IS NULL OR c.userMessage = :userMessage)")
   Optional<List<ContactUsForm>> findByUserAttributes(
            @Param("userEmail") String userEmail,
            @Param("userName") String userName,
            @Param("userPhone") String userPhone,
            @Param("userMessage") String userMessage);
}
