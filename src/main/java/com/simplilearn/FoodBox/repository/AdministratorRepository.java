package com.simplilearn.FoodBox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.FoodBox.model.Administrator;

@Repository()
public interface AdministratorRepository extends MongoRepository<Administrator, String> {

}
