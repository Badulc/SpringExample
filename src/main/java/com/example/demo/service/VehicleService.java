package com.example.demo.service;

//java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Vehicle;
import com.example.demo.repo.VehicleRepo;

@Service // stores the main business logic of app

public class VehicleService implements ServiceIF<Vehicle> {

	private VehicleRepo repo;

	public VehicleService(VehicleRepo repo) {
		super();
		this.repo = repo;
	}

	public Vehicle create(Vehicle v) {
		Vehicle created = this.repo.save(v);
		return created;

	}

	public List<Vehicle> getAll() {
		return this.repo.findAll();

	}

	public Vehicle getOne(Integer id) {
		Optional<Vehicle> found = this.repo.findById(id);
		return found.get();
	}

	public Vehicle replace(Integer id, Vehicle newVehicle) {
		Vehicle existing = this.repo.findById(id).get();
		existing.setName(newVehicle.getName());
		existing.setDoors(newVehicle.getDoors());
		existing.setWheels(newVehicle.getWheels());
		Vehicle updated = this.repo.save(existing);
		return updated;
	}

	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);

	}

//SELECT * FROM Vehicle WHERE name=
	public List<Vehicle> getVeesByName(String name) {
		List<Vehicle> found = this.repo.findByNameIgnoreCase(name);
		return found;

	}

	// SELECT * FROM Vehicle WHERE wheels=
	public List<Vehicle> getVeesByWheels(Integer wheels) {
		List<Vehicle> found = this.repo.findByWheels(wheels);
		return found;
	}

	// SELECT * FROM Vehicle WHERE door=
	// 2nd
	public List<Vehicle> getVeesByDoors(Integer doors) {
		List<Vehicle> found = this.repo.findByDoors(doors);
		return found;
	}

}
