//package com.simplilearn.FoodBox;
//
//import com.simplilearn.FoodBox.model.Comment;
//import com.simplilearn.FoodBox.model.Customer;
//import com.simplilearn.FoodBox.model.Administrator;
//import com.simplilearn.FoodBox.model.Dish;
//import com.simplilearn.FoodBox.model.Order;
//import com.simplilearn.FoodBox.model.Restaurant;
//import com.simplilearn.FoodBox.model.RestaurantInfo;
//import com.simplilearn.FoodBox.model.SearchEngine;
//import com.simplilearn.FoodBox.repository.AdministratorRepository;
//import com.simplilearn.FoodBox.repository.CustomerRepository;
//import com.simplilearn.FoodBox.repository.OrderRepository;
//import com.simplilearn.FoodBox.repository.RestaurantRepository;
//import com.simplilearn.FoodBox.repository.SearchEngineRepository;
//import com.simplilearn.FoodBox.service.PasswordService;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//// This class is used to refresh the database. It will delete all existing data and add the default ones
//// The default data contains one customer, two restaurants, two finished orders. Each restaurant contains three dishes.
//
//@SpringBootApplication
//public class LoadDatabase implements CommandLineRunner {
//  @Autowired
//  private CustomerRepository customerRepository;
//  @Autowired
//  private AdministratorRepository adminRepository;
//  @Autowired
//  private RestaurantRepository restaurantRepository;
//  @Autowired
//  private OrderRepository orderRepository;
//  @Autowired
//  private SearchEngineRepository searchEngineRepository;
//  PasswordService passwordService = new PasswordService();
//
//  public static void main(String[] args) {
//    SpringApplication.run(LoadDatabase.class, args);
//  }
//
//  @Override
//  public void run(String... args) throws Exception {
//    customerRepository.deleteAll();
//    Customer customer1 = new Customer("user1", passwordService.generatePassword("user"),
//        "7739973942", "1512 NW 63rd St", "Seattle", "WA", "98107");
//    customerRepository.save(customer1);
//    adminRepository.deleteAll();
//    Administrator admin = new Administrator("admin", passwordService.generatePassword("admin"),
//        "999999999", "N/A", "N/A", "N/A", "00000");
//    adminRepository.save(admin);
//
//    RestaurantInfo restaurantInfo1 = new RestaurantInfo();
//    restaurantInfo1.setOpen(true);
//    restaurantInfo1.setCousine("Burguers");
//    restaurantInfo1.setRestaurantName("Burguer King");
//    restaurantInfo1.setDescription("Best Burguers!");
//    restaurantInfo1.setImageUrl(
//        "https://bk-cms-dev.s3.amazonaws.com/_800x600_crop_center-center_none/Burger-King-Novo-logo.png?mtime=20210125132538&focal=none&tmtime=20210726090340");
//    restaurantInfo1.setTag1("fastfood");
//    restaurantInfo1.setTag2("burguer");
//    restaurantInfo1.setTag3("hamburger");
//
//    RestaurantInfo restaurantInfo2 = new RestaurantInfo();
//    restaurantInfo2.setOpen(true);
//    restaurantInfo1.setCousine("Pizza");
//    restaurantInfo2.setRestaurantName("Domino's Pizza");
//    restaurantInfo2.setDescription("BestPizza!");
//    restaurantInfo2.setImageUrl(
//        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN8KP29vdUJseIajxUpzV3QwrSVG_rUU-ZOg&usqp=CAU");
//    restaurantInfo2.setTag1("italian");
//    restaurantInfo2.setTag2("pizza");
//    restaurantInfo2.setTag3("fastfood");
//
//    RestaurantInfo restaurantInfo3 = new RestaurantInfo();
//    restaurantInfo3.setOpen(false);
//    restaurantInfo3.setRestaurantName("Homer's Bar");
//    restaurantInfo3.setDescription("Best drinks!");
//    restaurantInfo3.setImageUrl(
//        "https://img.elo7.com.br/product/main/2F2B0AC/arquivo-topo-de-bolo-homer-simpson-chop-em-camadas-arquivo.jpg"
//    );
//    restaurantInfo3.setTag1("alcohol");
//    restaurantInfo3.setTag2("beer");
//    restaurantInfo3.setTag3("drink");
//
//    Dish dish1 = new Dish();
//    dish1.setDishName("Whopper");
//    dish1.setImageUrl(
//        "https://bk-cms-dev.s3.amazonaws.com/Whopper_thumb_639x324-100k_2020-12-18-193933.png?mtime=20201218143933&focal=none");
//    dish1.setPrice(5);
//
//    Dish dish2 = new Dish();
//    dish2.setDishName("Chicken Fries");
//    dish2.setImageUrl("https://wp-content.bluebus.com.br/wp-content/uploads/2014/08/BKChickenFries.jpg");
//    dish2.setPrice(4);
//
//    Dish dish3 = new Dish();
//    dish3.setDishName("Fries");
//    dish3
//        .setImageUrl("https://www.pngkit.com/png/detail/61-612899_food-cooking-burger-king-french-fries-png.png");
//    dish3.setPrice(3);
//
//    Dish dish4 = new Dish();
//    dish4.setDishName("Pepperoni");
//    dish4.setImageUrl(
//        "https://www.qsrmagazine.com/sites/default/files/styles/story_page/public/story/pizza-hut-turns-comeback-expert_0.jpg?itok=U_V-5YAD");
//    dish4.setPrice(22);
//
//    Dish dish5 = new Dish();
//    dish5.setDishName("Muzzarela");
//    dish5.setImageUrl(
//        "https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/fozdoiguacu.portaldacidade.com/img/news/2018-11/pizza-hut-da-50-de-desconto-na-pizza-de-mussarela-na-semana-da-black-friday-5bf3519121563.png");
//    dish5.setPrice(13);
//
//    Dish dish6 = new Dish();
//    dish6.setDishName("Margherita");
//    dish6.setImageUrl(
//        "http://rossopizza.com.br/salao/wp-content/uploads/2019/09/istock-181175167.jpg");
//    dish6.setPrice(7.5);
//
//    Dish dish7 = new Dish();
//    dish7.setDishName("Beer");
//    dish7.setImageUrl("https://sochoppmaringa.com.br/wp-content/uploads/2019/08/chopp-pilsen-premium-600x600.jpeg");
//    dish7.setPrice(5);
//
//    List<Dish> dishes1 = new ArrayList<>();
//    dishes1.add(dish1);
//    dishes1.add(dish2);
//    dishes1.add(dish3);
//
//    List<Dish> dishes2 = new ArrayList<>();
//    dishes2.add(dish4);
//    dishes2.add(dish5);
//    dishes2.add(dish6);
//
//    List<Dish> dishes3 = new ArrayList<>();
//    dishes3.add(dish7);
//
//    restaurantRepository.deleteAll();
//    Restaurant restaurant1 = new Restaurant(restaurantInfo1, dishes1);
//    Restaurant restaurant2 = new Restaurant(restaurantInfo2, dishes2);
//    Restaurant restaurant3 = new Restaurant(restaurantInfo3, dishes3);
//    restaurantRepository.save(restaurant1);
//    restaurantRepository.save(restaurant2);
//    restaurantRepository.save(restaurant3);
//
//    searchEngineRepository.deleteAll();
//    SearchEngine searchEngine = new SearchEngine();
//    String id1 = restaurant1.getId();
//    searchEngine.add(restaurantInfo1.getRestaurantName(), id1);
//    searchEngine.add(restaurantInfo1.getTag1(), id1);
//    searchEngine.add(restaurantInfo1.getTag2(), id1);
//    searchEngine.add(restaurantInfo1.getTag3(), id1);
//    searchEngine.add(dish1.getDishName(), id1);
//    searchEngine.add(dish2.getDishName(), id1);
//    searchEngine.add(dish3.getDishName(), id1);
//    String id2 = restaurant2.getId();
//    searchEngine.add(restaurantInfo2.getRestaurantName(), id2);
//    searchEngine.add(restaurantInfo2.getTag1(), id2);
//    searchEngine.add(restaurantInfo2.getTag2(), id2);
//    searchEngine.add(restaurantInfo2.getTag3(), id2);
//    searchEngine.add(dish4.getDishName(), id2);
//    searchEngine.add(dish5.getDishName(), id2);
//    searchEngine.add(dish6.getDishName(), id2);
//    String id3 = restaurant3.getId();
//    searchEngine.add(restaurantInfo3.getRestaurantName(), id3);
//    searchEngine.add(restaurantInfo3.getTag1(), id3);
//    searchEngine.add(restaurantInfo3.getTag2(), id3);
//    searchEngine.add(restaurantInfo3.getTag3(), id3);
//    searchEngine.add(dish7.getDishName(), id3);
//    searchEngineRepository.save(searchEngine);
//
//    orderRepository.deleteAll();
//    Order order1 = new Order();
//    order1.setCustomerId(customer1.getId());
//    order1.setRestaurantId(restaurant1.getId());
//    order1.setStartTime(LocalDateTime.of(2020, 1, 1, 19, 30));
//    order1.setDelivery(true);
//    order1.setEndTime(LocalDateTime.of(2020, 1, 1, 20, 0));
//    order1.setContent(dishes1);
//    double price1 = 0;
//    for (Dish dish : dishes1) {
//      price1 += dish.getPrice();
//    }
//    order1.setPrice(price1);
//    Comment comment1 = new Comment(4, "very nice experience, the food is delicious");
//    order1.setComment(comment1);
//    Order order2 = new Order();
//    order2.setCustomerId(customer1.getId());
//    order2.setRestaurantId(restaurant2.getId());
//    order2.setStartTime(LocalDateTime.of(2020, 2, 3, 11, 25));
//    order2.setDelivery(true);
//    order2.setEndTime(LocalDateTime.of(2020, 2, 3, 13, 0));
//    order2.setContent(dishes2);
//    double price2 = 0;
//    for (Dish dish : dishes2) {
//      price2 += dish.getPrice();
//    }
//    order2.setPrice(price2);
//    Comment comment2 = new Comment(2, "the food is so expensive");
//    order2.setComment(comment2);
//    Order order3 = new Order();
//    order3.setCustomerId(customer1.getId());
//    order3.setRestaurantId(restaurant3.getId());
//    order3.setStartTime(LocalDateTime.of(2020, 1, 1, 19, 30));
//    order3.setDelivery(true);
//    order3.setEndTime(LocalDateTime.of(2020, 1, 1, 20, 0));
//    order3.setContent(dishes3);
//    double price3 = 0;
//    for (Dish dish : dishes3) {
//      price3 += dish.getPrice();
//    }
//    order3.setPrice(price3);
//    Order order4 = new Order();
//    order4.setCustomerId(customer1.getId());
//    order4.setRestaurantId(restaurant2.getId());
//    order4.setStartTime(LocalDateTime.of(2020, 2, 3, 11, 25));
//    order4.setDelivery(true);
//    order4.setEndTime(LocalDateTime.of(2020, 2, 3, 13, 0));
//    order4.setContent(dishes2);
//    double price4 = 0;
//    for (Dish dish : dishes2) {
//      price4 += dish.getPrice();
//    }
//    order4.setPrice(price4);
//    Comment comment3 = new Comment(5, "Great Place!!!");
//    order4.setComment(comment3);
//    orderRepository.save(order1);
//    orderRepository.save(order2);
//    orderRepository.save(order3);
//    orderRepository.save(order4);
//  }
//}
