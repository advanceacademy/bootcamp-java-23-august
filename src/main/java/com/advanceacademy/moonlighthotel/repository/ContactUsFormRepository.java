package com.advanceacademy.moonlighthotel.repository;

import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactUsFormRepository extends JpaRepository<ContactUsForm, Long> {

}
