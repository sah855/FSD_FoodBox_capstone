package com.simplilearn.FoodBox.service;

import java.util.List;
import java.util.Optional;

import com.simplilearn.FoodBox.model.Comment;
import com.simplilearn.FoodBox.model.Dish;
import com.simplilearn.FoodBox.model.Restaurant;
import com.simplilearn.FoodBox.model.RestaurantInfo;

public interface RestaurantService<T> {
	
	T addRestaurant();
	
	int deleteRestaurant(String id);

	int addDish(String id, Dish dish);

	int removeDish(String id, Dish dish);

	List<Dish> getAllDishes(String id);

	RestaurantInfo getInformation(String id);

	int updateInfo(String id, RestaurantInfo info);		  

	Optional<T> getRestaurant(String id);

	String getRestaurantIdByName(String name);

	Optional<T> getRestaurantByName(String name);

	List<T> getRestaurants();
}
