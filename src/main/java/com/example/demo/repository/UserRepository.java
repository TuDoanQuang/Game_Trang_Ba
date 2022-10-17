package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
