package com.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private String userName;
	private String email;
	private LocalDate birthDate;
	
	private List<String> tastes = new ArrayList<>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@JsonIgnore
	public List<String> getTastes() {
		return tastes;
	}
	public void setTastes(List<String> tastes) {
		this.tastes = tastes;
	}
	
	
}