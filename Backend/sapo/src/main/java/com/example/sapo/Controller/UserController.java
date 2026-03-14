package com.example.sapo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sapo.Dto.UserCreation;
import com.example.sapo.Service.UsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UsersService userServices;

    public UserController(UsersService userServices) {
        this.userServices = userServices;
    }

    @PostMapping(path = "/create_user")
    public ResponseEntity<?> createUser(@RequestBody UserCreation requests) {
        try {
            userServices.createUsers(requests);
            return ResponseEntity.ok().body("Tao tai khoan thanh cong!!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Tao tai khoan that bai!!!");
        }
    }
}