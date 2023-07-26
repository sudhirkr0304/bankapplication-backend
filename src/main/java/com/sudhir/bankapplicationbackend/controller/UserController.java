package com.sudhir.bankapplicationbackend.controller;

import com.sudhir.bankapplicationbackend.dto.LoginDto;
import com.sudhir.bankapplicationbackend.dto.RegisterUserDto;
import com.sudhir.bankapplicationbackend.dto.SuccessResponse;
import com.sudhir.bankapplicationbackend.entity.User;
import com.sudhir.bankapplicationbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/register")
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {

        if (userRepository.findByUsername(registerUserDto.getUsername()) != null) {
            return new ResponseEntity<>(new SuccessResponse("registration failed", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        User user = new User(
                registerUserDto.getUsername(), registerUserDto.getPassword(), registerUserDto.getName(), 0.0,0.0
        );
        userRepository.save(user);
        return new ResponseEntity<>(new SuccessResponse("registration successfull", HttpStatus.OK), HttpStatus.OK);

    }


    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        // Perform user authentication (e.g., using Spring Security or custom logic).
        // Return an authentication token (JWT) upon successful login.
        // For simplicity, we'll assume successful login here and return a dummy token.

        // In a real-world scenario, you should validate the user's credentials
        // and return a JWT token after successful authentication.

        User authenticatedUser = userRepository.findByUsername(loginDto.getUsername());
        if (authenticatedUser != null && authenticatedUser.getPassword().equals(loginDto.getPassword())) {
//            String token = jwtTokenUtil.generateToken(user.getUsername());
//            return ResponseEntity.ok(token);

            return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

//        String token = "dummy_jwt_token";
//        return ResponseEntity.ok(token);
    }

    @GetMapping("/user/details")
    public User getUserDetails(@RequestParam String username) {
        return  userRepository.findByUsername(username);
    }
}
