package com.simplilearn.FoodBox.controller;

import com.google.gson.Gson;
import com.simplilearn.FoodBox.exception.DishNotExistException;
import com.simplilearn.FoodBox.exception.OrderNotFinishedException;
import com.simplilearn.FoodBox.exception.RestaurantNotExistException;
import com.simplilearn.FoodBox.model.Comment;
import com.simplilearn.FoodBox.model.Dish;
import com.simplilearn.FoodBox.model.Order;
import com.simplilearn.FoodBox.model.Restaurant;
import com.simplilearn.FoodBox.model.RestaurantInfo;
import com.simplilearn.FoodBox.service.OrderServiceImpl;
import com.simplilearn.FoodBox.service.RestaurantServiceImpl;
import com.simplilearn.FoodBox.service.SearchEngineServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

  private final RestaurantServiceImpl restaurantService;
  private final OrderServiceImpl orderService;
  private final SearchEngineServiceImpl searchEngineService;

  @Autowired
  public RestaurantController(RestaurantServiceImpl restaurantService,
      OrderServiceImpl orderService, SearchEngineServiceImpl searchEngineService) {
    this.restaurantService = restaurantService;
    this.orderService = orderService;
    this.searchEngineService = searchEngineService;
  }

  @GetMapping(path = "/all")
  public List<Restaurant> getAllRestaurants() {
    return restaurantService.getRestaurants();
  }

  @GetMapping(path = "/search/" + "{query}")
  public List<Restaurant> SearchRestaurants(@PathVariable("query") String query) {
    List<Restaurant> res = new ArrayList<>();
    List<String> ids = searchEngineService.searchRestaurant(query);
    if (ids != null) {
      for (String id : ids) {
        if (restaurantService.getRestaurant(id).isPresent()) {
          res.add(restaurantService.getRestaurant(id).get());
        }
      }
    }
    return res;
  }

  @GetMapping(path = "{id}")
  public Restaurant getRestaurantById(@PathVariable("id") String id)
      throws RestaurantNotExistException {
    return restaurantService.getRestaurant(id)
        .orElseThrow(() -> new RestaurantNotExistException("Restaurant doesn't exist"));
  }

  @GetMapping(path = "/myActiveOrders/" + "{id}")
  public List<Order> getActiveOrders(@PathVariable("id") String id)
      throws RestaurantNotExistException {
    if (restaurantService.getRestaurant(id).isEmpty()) {
      throw new RestaurantNotExistException("The given restaurant doesn't exist");
    }
    return orderService.restaurantGetActiveOrders(id);
  }

  @GetMapping(path = "/myOrderHistory/" + "{id}")
  public List<Order> getOrderHistory(@PathVariable("id") String id)
      throws RestaurantNotExistException {
    if (restaurantService.getRestaurant(id).isEmpty()) {
      throw new RestaurantNotExistException("The given restaurant doesn't exist");
    }
    return orderService.restaurantFindPastOrders(id);
  }

  @GetMapping(path = "/menu/" + "{id}")
  public List<Dish> getMenu(@PathVariable("id") String id)
      throws RestaurantNotExistException {
    if (restaurantService.getRestaurant(id).isEmpty()) {
      throw new RestaurantNotExistException("The given restaurant doesn't exist");
    }
    return restaurantService.getAllDishes(id);
  }

  @PostMapping(path = "/addToMenu")
  public int addDishToMenu(@RequestBody String jsonDish)
      throws RestaurantNotExistException {
    JSONObject dish = new JSONObject(jsonDish);
    String restaurantId = dish.getString("restaurantId");
    String dishName = dish.getString("dishName");
    String imageUrl = dish.getString("imageUrl");
    double price = dish.getDouble("price");
    Dish newDish = new Dish(dishName, price, imageUrl);
    int res = restaurantService.addDish(restaurantId, newDish);
    if (res == -1) {
      throw new RestaurantNotExistException("The given restaurant doesn't exist");
    }
    // handle search engine
    searchEngineService.addRestaurant(dishName, restaurantId);
    return res;
  }

  @PostMapping(path = "/removeDish")
  public int removeDishFromMenu(@RequestBody String jsonDish)
      throws RestaurantNotExistException, DishNotExistException {
    JSONObject dish = new JSONObject(jsonDish);
    String restaurantId = dish.getString("restaurantId");
    Object dishObject = dish.getJSONObject("dish");
    Gson gson = new Gson();
    Dish newDish = gson.fromJson(dishObject.toString(), Dish.class);
    int res = restaurantService.removeDish(restaurantId, newDish);
    if (res == -1) {
      throw new RestaurantNotExistException("The given restaurant doesn't exist");
    }
    if (res == 0) {
      throw new DishNotExistException("The given dish doesn't exist");
    }
    // handle search engine
    searchEngineService.removeRestaurant(newDish.getDishName(), restaurantId);
    return res;
  }

  @GetMapping(path = "/information/" + "{id}")
  public RestaurantInfo getRestaurantInformation(@PathVariable("id") String id)
      throws RestaurantNotExistException {
    if (restaurantService.getInformation(id) != null) {
      return restaurantService.getInformation(id);
    }
    throw new RestaurantNotExistException("The given restaurant doesn't exist");
  }

  @PostMapping(path = "/information")
  public int updateRestaurantInformation(@RequestBody String jsonInfo)
      throws RestaurantNotExistException {
    JSONObject object = new JSONObject(jsonInfo);
    String restaurantId = object.getString("restaurantId");
    String cousine = object.getString("cousine");
    boolean open = object.getBoolean("status");
    String name = object.getString("name");
    String description = object.getString("description");
    String imageUrl = object.getString("imageUrl");
    String tag1 = object.getString("tag1");
    String tag2 = object.getString("tag2");
    String tag3 = object.getString("tag3");
    RestaurantInfo newInfo = new RestaurantInfo(cousine, open, name, description, imageUrl, tag1, tag2,
        tag3);
    // handle search engine
    RestaurantInfo oldInfo = restaurantService.getInformation(restaurantId);
    if (oldInfo != null) {
      searchEngineService.eraseInfo(oldInfo, restaurantId);
    }
    searchEngineService.updateInfo(newInfo, restaurantId);
    int res = restaurantService.updateInfo(restaurantId, newInfo);
    if (res == -1) {
      throw new RestaurantNotExistException("The given restaurant doesn't exist");
    }
    return res;
  }

  @DeleteMapping(path = "{id}")
  public int deleterRestaurant(@PathVariable("id") String id)
      throws RestaurantNotExistException, OrderNotFinishedException {
    if (orderService.restaurantGetActiveOrders(id).size() != 0) {
      throw new OrderNotFinishedException("You still have active orders, please finish them first");
    }
    // handle search engine
    RestaurantInfo oldInfo = restaurantService.getInformation(id);
    if (oldInfo != null) {
      searchEngineService.eraseInfo(oldInfo, id);
    }
    List<Dish> dishes = restaurantService.getAllDishes(id);
    if (dishes != null) {
      searchEngineService.eraseDishes(dishes, id);
    }
    int res = restaurantService.deleteRestaurant(id);
    if (res == -1) {
      throw new RestaurantNotExistException("Restaurant doesn't exist");
    }
    return res;
  }


  @GetMapping(path = "/getComments/" + "{id}")
  public List<Comment> findCommentsByRestaurant(@PathVariable("id") String id)
      throws RestaurantNotExistException {
    Optional<Restaurant> restaurantOptional = restaurantService.getRestaurant(id);
    if (restaurantOptional.isEmpty()) throw new RestaurantNotExistException("Restaurant doesn't exist");
    return orderService.restaurantGetComments(id);
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({RestaurantNotExistException.class, DishNotExistException.class,
      OrderNotFinishedException.class})
  public String handleException(Exception e) {
    return e.getMessage();
  }
}
