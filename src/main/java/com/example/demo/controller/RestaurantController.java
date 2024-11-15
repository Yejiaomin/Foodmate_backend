package com.example.demo.controller;

import com.example.demo.entity.Restaurant;
import com.example.demo.entity.User;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Restaurant API is working!";
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurantRepository.findByEmail(restaurant.getEmail()) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already registered"));
        } else {
            restaurantRepository.save(restaurant);
            return ResponseEntity.ok(Map.of("message", "Restaurant registered successfully"));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
    }
}