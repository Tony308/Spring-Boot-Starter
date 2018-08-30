package com.qa.springboot.dvd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springboot.dvd.exceptionHandler.ResourceNotFoundException;
import com.qa.springboot.dvd.model.DvdModel;
import com.qa.springboot.dvd.repository.DvdRepository;

@RestController
@RequestMapping("/dvd")
public class DvdController {

	@Autowired
	DvdRepository myRepository;
	
	@GetMapping("/getAll")
	public List<DvdModel> getDvds() {
		return myRepository.findAll();
	}
	@GetMapping("/{id}")
	public DvdModel getDvdById(@PathVariable(value = "id") Long DvdId) {
		return myRepository.findById(DvdId).orElseThrow(()-> new ResourceNotFoundException("Dvd", "id", DvdId));
	}
	
	@PostMapping("/add")
	public DvdModel createDVD (@Validated @RequestBody DvdModel data) {
		return myRepository.save(data);
	}
	
	@PutMapping("/{id}")
	public DvdModel updateDVD(@PathVariable(value = "id") Long DvdId,
		@Valid @RequestBody DvdModel DvdDetails) {
		DvdModel mSDM = myRepository.findById(DvdId).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", DvdId));
		
		mSDM.setName(DvdDetails.getName());
		DvdModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long DvdId) {
		DvdModel mSDM = myRepository.findById(DvdId).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", DvdId));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
}
