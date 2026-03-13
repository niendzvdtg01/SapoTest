package com.example.sapo.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sapo.Dto.UserCreation;
import com.example.sapo.Entity.Users;
import com.example.sapo.JpaRepository.UsersRepository;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users createUsers(UserCreation requests) {
        Users user = new Users();
        user.setEmail(requests.getEmail());
        user.setUsername(requests.getEmail());
        // hash password
        String passwordHash = encoder.encode(requests.getPassword());
        user.setPassword(passwordHash);
        user.setCreatedAt(java.time.LocalDateTime.now());
        return usersRepository.save(user);
    }
}
