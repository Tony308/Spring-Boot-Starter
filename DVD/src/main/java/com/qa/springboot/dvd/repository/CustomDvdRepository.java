package com.qa.springboot.dvd.repository;

import com.qa.springboot.dvd.model.DvdModel;

import java.util.Optional;

public interface CustomDvdRepository {
    Optional<DvdModel> findByTitle(String title);
}
