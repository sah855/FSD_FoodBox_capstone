package com.simplilearn.FoodBox.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Restaurant {
	
	@Id
	private String id;

	private RestaurantInfo information;
	private List<Dish> menu;

	public Restaurant(RestaurantInfo information, List<Dish> menu) {
		this.information = information;
		this.menu = menu;
	}

	public Restaurant() {}
	
	public String getId() {
	    return id;
	  }

	public void setId(String id) {
		this.id = id;
	}

	public RestaurantInfo getInformation() {
		return information;
	}

	public void setInformation(RestaurantInfo information) {
		this.information = information;
	}

	public List<Dish> getMenu() {
		return menu;
	}

	public void setMenu(List<Dish> menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Restaurant{" + "information=" + information + ", menu=" + menu + '}';
	}
}
