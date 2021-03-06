package com.qa.springboot.dvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springboot.dvd.model.DvdModel;

@Repository
public interface DvdRepository extends JpaRepository<DvdModel, Long>, CustomDvdRepository{
}