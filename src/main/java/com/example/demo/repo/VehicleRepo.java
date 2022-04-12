package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

	// spring will auto generate all of the basic crud functionality

	List<Vehicle> findByNameIgnoreCase(String name);

	List<Vehicle> findByWheels(Integer wheels);

	// first
	List<Vehicle> findByDoors(Integer doors);

}
