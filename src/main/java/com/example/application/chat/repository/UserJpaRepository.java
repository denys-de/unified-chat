package com.example.application.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Denys Babich
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
