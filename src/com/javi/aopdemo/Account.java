package com.javi.aopdemo;

public class Account {
	
	//attributes
	private String name;
	private String level;
	
	//default contructor (no arg constructor):
	public Account() {
		
	}
	
	
	//constructor
	public Account(String name, String level) {
		this.name = name;
		this.level = level;
	}



	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	// toString() method
	@Override
	public String toString() {
		return "Account [name=" + name + ", level=" + level + "]";
	}
	
	

}
