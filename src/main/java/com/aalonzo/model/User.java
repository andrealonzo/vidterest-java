package com.aalonzo.model;

public class User {
	private int id;
	private String email;
	private String password;
	private String imageUrl;
	private String displayName;
	private String city;
	private String state;
	public User(int id, String email, String password, String imageUrl, String displayName, String city, String state) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.imageUrl = imageUrl;
		this.displayName = displayName;
		this.city = city;
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
