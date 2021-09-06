package com.simplilearn.FoodBox.model;

public class Administrator extends User {

  public Administrator() {
    this.setType("admin");
  }

  public Administrator(String userName, String password, String phoneNumber, String address,
      String city, String state, String zip) {
    super(userName, password, phoneNumber, address, city, state, zip);
    this.setType("admin");
  }
}
