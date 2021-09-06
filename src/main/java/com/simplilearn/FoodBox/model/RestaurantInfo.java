package com.simplilearn.FoodBox.model;

public class RestaurantInfo {

	private boolean open;
	private String restaurantName;
	private String description;
	private String imageUrl;
	private String tag1;
	private String tag2;
	private String tag3;
	private String cousine;

	public RestaurantInfo() {
	}

	public RestaurantInfo(String cousine, boolean open, String restaurantName, String description, String imageUrl, String tag1,
			String tag2, String tag3) {
		this.cousine = cousine;
		this.open = open;
		this.restaurantName = restaurantName;
		this.description = description;
		this.imageUrl = imageUrl;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}

	public String getCousine() {
		return cousine;
	}

	public void setCousine(String cousine) {
		this.cousine = cousine;
	}

	@Override
	public String toString() {
		return "RestaurantInfo{" + "cousine= "+ cousine + "open=" + open + ", restaurantName='" + restaurantName + '\'' + ", description='"
				+ description + '\'' + ", imageUrl='" + imageUrl + '\'' + ", tag1='" + tag1 + '\'' + ", tag2='" + tag2
				+ '\'' + ", tag3='" + tag3 + '\'' + '}';
	}
}
