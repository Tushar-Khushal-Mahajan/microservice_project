package com.userentity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userentity.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

  // TODO: ADD METHODS IF REQURIED
}
