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

import com.qa.springboot.dvd.exceptionHandler.ResourceNotFoundException;
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
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long UserId) {
		UserModel MSDM = userRepo.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
		userRepo.delete(MSDM);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public UserModel updateUser(@PathVariable(value = "id" ) Long UserId,
			@Valid @RequestBody UserModel userDetails
			) {
		UserModel MSDM = userRepo.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
		
		MSDM.setFirstName(userDetails.getFirstName());
		MSDM.setLastName(userDetails.getLastName());
		MSDM.setDoB(userDetails.getDoB());
		UserModel updatedData = userRepo.save(MSDM);
		return updatedData;
		
	}
}
