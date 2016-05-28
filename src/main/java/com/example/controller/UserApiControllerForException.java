package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.exception.EntityNotFoundException;
import com.example.repository.UserRepositoryForException;

@RestController
@RequestMapping("/exception/api")
public class UserApiControllerForException {
	private UserRepositoryForException userRepository;

	@Autowired
	public UserApiControllerForException(UserRepositoryForException userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)

	public List<User> findAll() {
		return userRepository.findAll();
	}

	// @RequestBody请求json格式
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.OK;
		if (!userRepository.exists(user.getEmail())) {
			status = HttpStatus.CREATED;
		}
		User saved = userRepository.save(user);
		return new ResponseEntity<>(saved, status);
	}

	@RequestMapping(value = "/user/{email}", method = {RequestMethod.PUT,RequestMethod.GET})
	public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user)
			throws EntityNotFoundException {
		User saved = userRepository.update(user.getEmail(),user);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable String email) throws EntityNotFoundException {
		if (!userRepository.exists(email)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userRepository.delete(email);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}