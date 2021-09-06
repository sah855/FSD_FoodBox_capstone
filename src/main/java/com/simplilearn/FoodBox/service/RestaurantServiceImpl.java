package com.simplilearn.FoodBox.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.FoodBox.model.Comment;
import com.simplilearn.FoodBox.model.Dish;
import com.simplilearn.FoodBox.model.Restaurant;
import com.simplilearn.FoodBox.model.RestaurantInfo;
import com.simplilearn.FoodBox.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService<Restaurant> {

  @Autowired
  RestaurantRepository restaurantRepository;
  PasswordService passwordService = new PasswordService();


  @Override
  public int addDish(String id, Dish dish) {
    Optional<Restaurant> restaurant = this.getRestaurant(id);
    if (restaurant.isPresent()) {
      Set<Dish> set;
      if (restaurant.get().getMenu() == null) {
        set = new HashSet<>();
      } else {
        set = new HashSet<>(restaurant.get().getMenu());
      }
      set.add(dish);
      restaurant.get().setMenu(new ArrayList<>(set));
      restaurantRepository.save(restaurant.get());

      System.out.println("Add the dish");
      return 1;
    }
    System.out.println("Can't add the dish");
    return -1;
  }

  @Override
  public int removeDish(String id, Dish dish) {
    Optional<Restaurant> restaurant = this.getRestaurant(id);
    if (restaurant.isPresent()) {
      List<Dish> temp = restaurant.get().getMenu();
      if (temp.contains(dish)) {
        temp.remove(dish);
        restaurant.get().setMenu(temp);
        restaurantRepository.save(restaurant.get());

        System.out.println("Remove the dish");
        return 1;
      } else {
        System.out.println("Dish not in the menu");
        return 0;
      }
    }
    System.out.println("Can't remove the dish");
    return -1;
  }

  @Override
  public List<Dish> getAllDishes(String id) {
    Optional<Restaurant> restaurant = this.getRestaurant(id);
    System.out.println("Get all dishes from restaurant: " + id);
    return restaurant.map(Restaurant::getMenu).orElse(null);
  }

  @Override
  public RestaurantInfo getInformation(String id) {
    Optional<Restaurant> restaurant = this.getRestaurant(id);
    if (restaurant.isPresent()) {
      System.out.println("Get the restaurant information");
      if (restaurant.get().getInformation() == null) {
        return new RestaurantInfo();
      } else {
        return restaurant.get().getInformation();
      }
    }
    return null;
  }

  @Override
  public int updateInfo(String id, RestaurantInfo info) {
    Optional<Restaurant> restaurant = this.getRestaurant(id);
    if (restaurant.isPresent()) {

      restaurant.get().setInformation(info);
      restaurantRepository.save(restaurant.get());
      System.out.println("Update the information");
      return 1;
    }
    System.out.println("Can't update the information");
    return -1;
  }

  @Override
  public Restaurant addRestaurant() {
      Restaurant restaurant = new Restaurant();
      restaurantRepository.insert(restaurant);
      System.out.println("Restaurant added to the database");
      return restaurant;
  }

  public int deleteRestaurant(String id) {
    if (this.getRestaurant(id).isPresent()) {
      restaurantRepository.deleteById(id);
      System.out.println("Restaurant deleted from the database");
      return 1;
    }
    System.out.println("Restaurant can't be deleted from the database");
    return -1;
  }

  @Override
  public Optional<Restaurant> getRestaurant(String id) {
    if (id != null) {
      return restaurantRepository.findById(id);
    }
    return Optional.empty();
  }

  @Override
  public String getRestaurantIdByName(String name) {
    List<Restaurant> restaurants = this.getRestaurants();
    for (Restaurant restaurant : restaurants) {
      if (restaurant.getInformation().getRestaurantName().equals(name)) {
        return restaurant.getId();
      }
    }
    System.out.println("Given name doesn't found in restaurant database");
    return null;
  }

  @Override
  public Optional<Restaurant> getRestaurantByName(String name) {
    return this.getRestaurant(getRestaurantIdByName(name));
  }

  @Override
  public List<Restaurant> getRestaurants() {
    return restaurantRepository.findAll();
  }

}
