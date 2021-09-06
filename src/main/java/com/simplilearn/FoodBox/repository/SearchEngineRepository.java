package com.simplilearn.FoodBox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simplilearn.FoodBox.model.SearchEngine;

public interface SearchEngineRepository extends MongoRepository<SearchEngine, String> {

}
