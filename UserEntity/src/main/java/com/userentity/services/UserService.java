package com.userentity.services;

import java.util.List;

import com.userentity.entities.User;

public interface UserService {

  // DONE: Get all users
  public List<User> getAllUsers();

  // DONE: Get User By ID
  public User getUserById(String userId);

  // DONE: Insert User
  public User InsertUser(User user);
}
