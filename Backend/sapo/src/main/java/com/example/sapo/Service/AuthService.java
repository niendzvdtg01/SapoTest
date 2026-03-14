package com.example.sapo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sapo.Entity.Users;
import com.example.sapo.JpaRepository.UsersRepository;

@Service
public class AuthService {
    @Autowired
    private UsersRepository usersRespository;

    public Users authencate(String username, String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Users user = usersRespository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));
        if (!encoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Incorrect password!!!");
        }
        return user;
    }
}