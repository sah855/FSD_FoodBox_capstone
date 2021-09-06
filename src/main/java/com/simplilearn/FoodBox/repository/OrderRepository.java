package com.simplilearn.FoodBox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.FoodBox.model.Order;

@Repository()
public interface OrderRepository extends MongoRepository<Order, String> {

}
