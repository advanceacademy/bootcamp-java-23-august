package com.advanceacademy.moonlighthotel.repository.user;

import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    //This query method will search in database for the role we want, and will return a role if present or not if there is no matches.
    UserRole findByUserRole(String userRole);
}
