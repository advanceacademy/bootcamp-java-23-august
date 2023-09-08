package com.advanceacademy.moonlighthotel.repository;

import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ContactUsFormRepository extends JpaRepository<ContactUsForm, Long> {

    @Query(nativeQuery=true, value = "SELECT * FROM contact_us WHERE user_email LIKE TRIM(?1)")
    Optional <List<ContactUsForm>> findByUserEmail(String userEmail);

    @Query(nativeQuery=true, value = "SELECT * FROM contact_us WHERE user_phone_number LIKE TRIM(?1)")
    Optional <List<ContactUsForm>> findByUserPhone (String userPhone);
    @Query(nativeQuery=true, value = "SELECT * FROM contact_us WHERE user_name LIKE TRIM(?1)")
    Optional <List<ContactUsForm>> findByUserName (String userName);

    @Query(nativeQuery=true, value = "SELECT * FROM contact_us WHERE user_message LIKE %?1% OR user_message LIKE TRIM(?1)")
    Optional <List <ContactUsForm>>findByMessageContaining (String message);

}
