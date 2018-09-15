package com.qa.springboot.dvd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springboot.dvd.exception.handler.ResourceNotFoundException;
import com.qa.springboot.dvd.model.UserModel;
import com.qa.springboot.dvd.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/getAll")
	public List<UserModel> getUsers() {
		return userRepo.findAll();
	}
	
	@PostMapping("/add")
	public UserModel createUser(@Valid @RequestBody UserModel data) {
		return userRepo.save(data);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long userId) {
		UserModel msdm = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepo.delete(msdm);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public UserModel updateUser(@PathVariable(value = "id" ) Long userId,
			@Valid @RequestBody UserModel userDetails
			) {
		UserModel msdm = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		msdm.setFirstName(userDetails.getFirstName());
		msdm.setLastName(userDetails.getLastName());
		msdm.setDob(userDetails.getDob());
		return userRepo.save(msdm);
	}
}
