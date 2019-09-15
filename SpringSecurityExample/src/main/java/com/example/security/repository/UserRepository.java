package com.example.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.security.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	Optional<User> findByUserName(String userName);
}
