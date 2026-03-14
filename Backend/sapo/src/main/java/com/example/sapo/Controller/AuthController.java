package com.example.sapo.Controller;

import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sapo.Dto.LoginRequests;
import com.example.sapo.Entity.Users;
import com.example.sapo.Security.jwtUtils;
import com.example.sapo.Service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthService authServices;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequests requests, HttpServletResponse response) {
        Users user = authServices.authencate(requests.getUsername(), requests.getPassword());
        String token = jwtUtils.generateToken(user);

        ResponseCookie cookie = ResponseCookie.from("access_cookie", token)
                .httpOnly(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofMinutes(60))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok("Login sucessfully!!!");
    }

}
