package com.simplilearn.FoodBox.controller;

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

import com.simplilearn.FoodBox.exception.PasswordNotMatchException;
import com.simplilearn.FoodBox.exception.UserAlreadyExistException;
import com.simplilearn.FoodBox.exception.UserNotExistException;
import com.simplilearn.FoodBox.model.Administrator;
import com.simplilearn.FoodBox.service.AdministratorServiceImpl;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

  private final AdministratorServiceImpl adminService;

  @Autowired
  public AdministratorController(AdministratorServiceImpl adminService) {
    this.adminService = adminService;
  }

  @GetMapping(path = "{id}")
  public Administrator getAdministratorById(@PathVariable("id") String id)
      throws UserNotExistException {
    return adminService.getUser(id)
        .orElseThrow(() -> new UserNotExistException("User doesn't exist"));
  }

  @PostMapping(path = "/login")
  public Administrator loginAdministrator(@RequestBody String jsonUser)
      throws UserNotExistException, PasswordNotMatchException {

    JSONObject user = new JSONObject(jsonUser);
    String userName = user.getString("userName");
    String password = user.getString("password");
    Optional<Administrator> admin = adminService.getUserByName(userName);
    if (admin.isEmpty()) {
      throw new UserNotExistException("User doesn't exist");
    }
    if (!adminService.passwordMatch(admin.get().getId(), password)) {
      throw new PasswordNotMatchException("User Name or Password don't match");
    }
    return admin.get();
  }

  @PostMapping(path = "/register")
  public Administrator registerAdministrator(@RequestBody String jsonUser)
      throws UserAlreadyExistException {

    JSONObject user = new JSONObject(jsonUser);
    String userName = user.getString("userName");
    String password = user.getString("password");
    String phoneNumber = user.getString("phoneNumber");
    String address = user.getString("address");
    String city = user.getString("city");
    String state = user.getString("state");
    String zip = user.getString("zip");
    Administrator admin = adminService.addUser(userName, password, phoneNumber, address, city, state, zip);
    if (admin == null) {
      throw new UserAlreadyExistException("User already exists, please login");
    }
    return admin;
  }

  @PostMapping(path = "/logout")
  public int logoutCustomer() {
    System.out.println("logout the user");
    return 1;
  }


  @DeleteMapping(path = "{id}")
  public int deleterAdministrator(@PathVariable("id") String id)
      throws UserNotExistException {
    int res = adminService.deleteUser(id);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist, please register");
    }
    return res;
  }

  @PostMapping(path = "/resetPassword")
  public int resetPassword(@RequestBody String jsonPassword)
      throws UserNotExistException, PasswordNotMatchException {
    JSONObject object = new JSONObject(jsonPassword);
    String id = object.getString("id");
    String password = object.getString("password");
    String newPassword = object.getString("newPassword");
    int res = adminService.updatePassword(id, password, newPassword);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist, please register");
    }
    if (res == 0) {
      throw new PasswordNotMatchException("User Name or Password don't match");
    }
    return res;
  }

  @PostMapping(path = "/resetPhone")
  public int resetPhoneNumber(@RequestBody String jsonPhone)
      throws UserNotExistException {
    JSONObject object = new JSONObject(jsonPhone);
    String id = object.getString("id");
    String phoneNumber = object.getString("phoneNumber");
    int res = adminService.updatePhoneNumber(id, phoneNumber);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist, please register");
    }
    return res;
  }

  @PostMapping(path = "/resetAddress")
  public int resetAddress(@RequestBody String jsonAddress)
      throws UserNotExistException {
    JSONObject object = new JSONObject(jsonAddress);
    String id = object.getString("id");
    String address = object.getString("address");
    String city = object.getString("city");
    String state = object.getString("state");
    String zip = object.getString("zip");
    int res = adminService.updateAddress(id, address, city, state, zip);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist, please register");
    }
    return res;
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({UserNotExistException.class, PasswordNotMatchException.class,
      UserAlreadyExistException.class})
  public String handleException(Exception e) {
    return e.getMessage();
  }
}
