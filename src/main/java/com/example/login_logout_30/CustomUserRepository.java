package com.example.login_logout_30;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    @Query("select c from CustomUser c where c.name = ?1")
    Optional<CustomUser> findByNameEquals(String name);

}