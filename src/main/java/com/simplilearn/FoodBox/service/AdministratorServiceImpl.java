package com.simplilearn.FoodBox.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.FoodBox.model.Administrator;
import com.simplilearn.FoodBox.repository.AdministratorRepository;

@Service
public class AdministratorServiceImpl implements UserService<Administrator> {

  @Autowired
  AdministratorRepository administratorRepository;
  PasswordService passwordService = new PasswordService();

  @Override
  public Administrator addUser(String userName, String password, String phoneNumber, String address,
      String city, String state, String zip) {
    if (this.getUserIdByName(userName) == null) {
      String newPassword = passwordService.generatePassword(password);
      Administrator admin = new Administrator(userName, newPassword, phoneNumber, address, city, state, zip);
      administratorRepository.insert(admin);
      System.out.println("Administrator added to the database");
      return admin;
    }
    System.out.println("Administrator can't be added to the database");
    return null;
  }

  @Override
  public int deleteUser(String id) {
    if (this.getUser(id).isPresent()) {
    	administratorRepository.deleteById(id);
      System.out.println("Administrator deleted from the database");
      return 1;
    }
    System.out.println("Administrator can't be deleted from the database");
    return -1;
  }

  @Override
  public Optional<Administrator> getUser(String id) {
    if (id != null) {
      return administratorRepository.findById(id);
    }
    return Optional.empty();
  }

  @Override
  public String getUserIdByName(String userName) {
    List<Administrator> admins = this.getUsers();
    for (Administrator admin : admins) {
      if (admin.getUserName().equals(userName)) {
        return admin.getId();
      }
    }
    System.out.println("Given userName doesn't found in administrator database");
    return null;
  }

  @Override
  public Optional<Administrator> getUserByName(String userName) {
    return this.getUser(getUserIdByName(userName));
  }

  @Override
  public List<Administrator> getUsers() {
    return administratorRepository.findAll();
  }

  @Override
  public boolean passwordMatch(String id, String password) {
    Optional<Administrator> admin = this.getUser(id);
    return admin.isPresent() && passwordService.passwordMatch(password, admin.get().getPassword());
  }

  @Override
  public int updatePassword(String id, String oldPassword, String newPassword) {
    Optional<Administrator> admin = this.getUser(id);
    if (admin.isPresent()) {
      if (this.passwordMatch(id, oldPassword)) {
        admin.get().setPassword(passwordService.generatePassword(newPassword));
        administratorRepository.save(admin.get());
        System.out.println("Update the password");
        return 1;
      } else {
        System.out.println("Password doesn't match");
        return 0;
      }
    }
    System.out.println("Can't update the password");
    return -1;
  }

  @Override
  public int updatePhoneNumber(String id, String newNumber) {
    Optional<Administrator> admin = this.getUser(id);
    if (admin.isPresent()) {
      admin.get().setPhoneNumber(newNumber);
      administratorRepository.save(admin.get());
      System.out.println("Update the phone number");
      return 1;
    }
    System.out.println("Can't update the phone number");
    return -1;
  }

  @Override
  public int updateAddress(String id, String address, String city, String state,
      String zip) {
    Optional<Administrator> admin = this.getUser(id);
    if (admin.isPresent()) {
      admin.get().setAddress(address);
      admin.get().setCity(city);
      admin.get().setState(state);
      admin.get().setZip(zip);
      administratorRepository.save(admin.get());
      System.out.println("Update the address");
      return 1;
    }
    System.out.println("Can't update the address");
    return -1;
  }
}
