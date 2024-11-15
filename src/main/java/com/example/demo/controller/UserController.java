package com.example.demo.controller;


import com.example.demo.entity.Restaurant;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestBody User user) {
//        if (userRepository.findByEmail(user.getEmail()) != null) {
//            return "User already exists, please login";
//        } else {
//            userRepository.save(user);
//            return "User registered successfully";
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already registered"));
        } else {
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
