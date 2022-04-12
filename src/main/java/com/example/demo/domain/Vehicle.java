package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Vehicle {
	
	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Integer wheels;
	
	@Column(nullable=false)
	private Integer doors;
	
	
	//constructors
	public Vehicle(Integer id, String name, Integer wheels, Integer doors) {
		super();
		this.id = id;
		this.name = name;
		this.wheels = wheels;
		this.doors = doors;
	}
	public Vehicle() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWheels() {
		return wheels;
	}
	public void setWheels(Integer wheels) {
		this.wheels = wheels;
	}
	public Integer getDoors() {
		return doors;
	}
	public void setDoors(Integer doors) {
		this.doors = doors;
	}
	
	// To String method
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", wheels=" + wheels + ", doors=" + doors + "]";
	}

}
