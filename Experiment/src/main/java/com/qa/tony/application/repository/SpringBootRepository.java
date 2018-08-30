package com.qa.tony.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tony.application.model.SpringBootDataModel;;

@Repository
public interface SpringBootRepository extends JpaRepository<SpringBootDataModel, Long>{

}
