package com.userentity.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.userentity.entities.User;
import com.userentity.services.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl serviceImpl;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = serviceImpl.getAllUsers();

		System.out.println("All users");

		return ResponseEntity.ok().body(users);
	}

	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {

		User user = serviceImpl.getUserById(userId);

		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

//	ratingHotelFallBack method for circuit breaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {

		logger.info("FALLBACK IS EXECUTED BECAUSE SERVICE IS DOWN", ex.getMessage());

		User user = new User("userId", "Dummy User", "dummy@gmail.com", null);

		return ResponseEntity.ok(user);
	}

//	----------------------------------
	@PostMapping
	public ResponseEntity<User> insertUser(@RequestBody User user) {

		System.out.println("User insert controller");

		User insertUser = serviceImpl.InsertUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(insertUser);
	}
}
