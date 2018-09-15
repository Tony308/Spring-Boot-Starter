package com.qa.springboot.dvd.controller;

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

import com.qa.springboot.dvd.exception.handler.ResourceNotFoundException;
import com.qa.springboot.dvd.model.DvdModel;
import com.qa.springboot.dvd.repository.DvdRepository;

import java.util.Date;
import java.util.List;

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
	public DvdModel getDvdById(@PathVariable(value = "id") Long dvdId) {
		return myRepository.findById(dvdId).orElseThrow(()-> new ResourceNotFoundException("Dvd", "id", dvdId));
	}
	
	@PostMapping("/add")
	public DvdModel createDVD (@Validated @RequestBody DvdModel data) {
		return myRepository.save(data);
	}
	
	@PutMapping("/{id}")
	public DvdModel updateDVD(@PathVariable(value = "id") Long dvdId,
		@Valid @RequestBody DvdModel dvdDetails) {
		DvdModel mSDM = myRepository.findById(dvdId).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", dvdId));
		mSDM.setTitle(dvdDetails.getTitle());
		return myRepository.save(mSDM);
	}
	
	@PutMapping("/rent/{id}")
	public DvdModel rentDVD(@PathVariable(value = "id") Long dvdId,
        @Valid @RequestBody DvdModel dvdDetails) {

		DvdModel msdm = myRepository.findById(dvdId).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", dvdId));

		Date currentDateTime = new Date();

		msdm.setCheckedout(dvdDetails.getCheckedout());
		msdm.setReference(dvdDetails.getReference());
		msdm.setTimeStamp(currentDateTime);
		return myRepository.save(msdm);

	}

	@PutMapping("/return/{id}")
    public DvdModel returnDVD(@PathVariable(value = "id") Long dvdId,
            @Valid @RequestBody DvdModel dvdDetails){

	    DvdModel msdm = myRepository.findById(dvdId).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", dvdId));

	    if (dvdDetails.getReference().equals(msdm.getReference())) {
	        msdm.setReference(null);
        }

	    msdm.setCheckedout(dvdDetails.getCheckedout());
        msdm.setTimeStamp(null);

        return myRepository.save(msdm);


    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") Long dvdId) {
		DvdModel mSDM = myRepository.findById(dvdId).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", dvdId));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
}
