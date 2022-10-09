package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// getting all user record by using the method findaAll() of CrudRepository
	public List<User> getAlluser() {
		List<User> user = new ArrayList<User>();
		userRepository.findAll().forEach(user1 -> user.add(user1));
		return user;
	}

	// getting a specific record by using the method findById() of CrudRepository
	public User getuserById(int id) {
		return userRepository.findById(id).get();
	}

	// saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}

	// deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	// updating a record
	public void update(User user, int userId) {
		userRepository.save(user);
	}
}
