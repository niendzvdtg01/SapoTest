package com.example.sapo.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sapo.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    //
}
