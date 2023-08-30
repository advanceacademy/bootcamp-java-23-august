package com.advanceacademy.moonlighthotel.repository.user;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //That query method will return an optional list with users from the database searching by firstname, or will return an empty list if there is no matches.
    Optional<List<User>> findByFirstName(String firstName);

    //That query method will return an optional list with users from the database searching by lastname, or will return an empty list if there is no matches.
    Optional<List<User>> findByLastName(String lastName);

    //That query method will return an optional list with users from the database searching by firstname and lastname, or will return an empty list if there is no matches.
    Optional<List<User>> findByFirstNameAndLastName(String firstName, String lastName);

    //That query method will return a user from the database if there is a match with phone number. Or will return an empty Optional if there is no matches.
    Optional<User> findByPhoneNumber(String phoneNumber);

    //That query method will return a user from the database if there is a match with email. Or will return an empty optional if there is no matches.
    Optional<User> findByEmail(String email);

    //That query method will return an optional list with users from the database searching by created date, or will return an empty list if there is no matches.
    Optional<List<User>> findByCreatedDate(LocalDate createdDate);

    //That query method will return an optional list with users from the database searching by their role, or will return an empty list if there is no matches.
    Optional<List<User>> findByUserRole(UserRole userRole);

    //Check whether a user with a given email exists in the database or not.
    boolean existsByEmail(String email);

    //Check whether a user with a given phone number exists in the database or not.
    boolean existsByPhoneNumber(String phoneNumber);
}
