package com.qa.tony.application.controller;

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

import com.qa.tony.application.exception.ResourceNotFoundException;
import com.qa.tony.application.model.SpringBootDataModel;
import com.qa.tony.application.repository.SpringBootRepository;

@RestController
@RequestMapping("/api")
public class SpringBootController {

	@Autowired
	SpringBootRepository myRepo;
	
	@PostMapping("/add")
	public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel MSDM) {
		return myRepo.save(MSDM);
	}
	
	@GetMapping("/person/{id}")
	public SpringBootDataModel getPersonByID(@PathVariable(value = "id")Long personID) {
		return myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("SpringBootDataModel", "id", personID));
	}
	
	@GetMapping("/getAll")
	public List<SpringBootDataModel> getAll() {
		return myRepo.findAll();
	}
	
	@PutMapping("/person/{id}")
	public SpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID, 
			@Valid @RequestBody SpringBootDataModel personDetails) {
	SpringBootDataModel mSDM = myRepo.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
	
	mSDM.setName(personDetails.getName());
	mSDM.setAddress(personDetails.getAddress());
	mSDM.setAge(personDetails.getAge());
	
	SpringBootDataModel updateData = myRepo.save(mSDM);
	return updateData;
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personID) {
		SpringBootDataModel mSDM = myRepo.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		
		myRepo.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
}