package com.advanceacademy.moonlighthotel.repository;

import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ContactUsFormRepository extends JpaRepository<ContactUsForm,Long> {
    @Query(nativeQuery = true,value = "SELECT c FROM ContactUsForm c WHERE c.userEmail = ?1")
      Optional<List<ContactUsForm>> findByUserEmail (String userEmail);

}
