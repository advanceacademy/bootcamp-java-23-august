package com.advanceacademy.moonlighthotel.repository;

import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactUsFormRepository extends JpaRepository<ContactUsForm, Long> {
    Optional <List<ContactUsForm>> findByUserPhone (String userPhone);
    Optional <List<ContactUsForm>> findByUserName (String userName);
    Optional <List <ContactUsForm>>findByMessageContaining (String userMessage);
}
