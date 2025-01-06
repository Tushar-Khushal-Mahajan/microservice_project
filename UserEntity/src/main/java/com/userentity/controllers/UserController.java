package com.userentity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.userentity.entities.User;
import com.userentity.services.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserServiceImpl serviceImpl;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = serviceImpl.getAllUsers();

    System.out.println("All users");

    return ResponseEntity.ok().body(users);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable String userId) {

    User user = serviceImpl.getUserById(userId);

    return ResponseEntity.status(HttpStatus.FOUND).body(user);
  }

  @PostMapping
  public ResponseEntity<User> insertUser(@RequestBody User user) {

    System.out.println("User insert controller");

    User insertUser = serviceImpl.InsertUser(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(insertUser);
  }
}
