package com.example.demo.web;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Vehicle;
import com.example.demo.service.VehicleService;

@RestController // tells spring this is a controller
//Rest Compliant

public class VehicleController {

	private VehicleService service;

	@Autowired // tell spring to fetch vehicle service from the context - dependency injection
	public VehicleController(VehicleService service) {
		super();
		this.service = service;
	}

	// CRUD Functionality
	// ResponseEntity is an extension of HTTPEntitiy that represents an http
	// response including status code, headers and body

	// create
	@PostMapping("/create") // 201 - created
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle v) {
		Vehicle created = this.service.create(v);
		ResponseEntity<Vehicle> response = new ResponseEntity<Vehicle>(created, HttpStatus.CREATED);
		return response;
	}

	// Read all method
	@GetMapping("/getAll") // 200 - ok
	public ResponseEntity<List<Vehicle>> getAllVees() {
		return ResponseEntity.ok(this.service.getAll());

	}

	// Read One Method
	@GetMapping("/get/{id}") // 200 - ok
	public Vehicle getVehicle(@PathVariable Integer id) {
		return this.service.getOne(id);

	}

	// Update Method
	@PutMapping("/replace/{id}") // 202 - accepted
	public ResponseEntity<Vehicle> replaceVehicle(@PathVariable Integer id, @RequestBody Vehicle newVehicle) {
		Vehicle body = this.service.replace(id, newVehicle);
		ResponseEntity<Vehicle> response = new ResponseEntity<Vehicle>(body, HttpStatus.ACCEPTED);
		return response;
	}

	// Delete method
	@DeleteMapping("/remove/{id}") // 204 - no content
	public ResponseEntity<?> removeVehicle(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// read by name
	//postman pat(h) "/getByName/{name}
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Vehicle>> getVehicleByName(@PathVariable String name) {
		List<Vehicle> found = this.service.getVeesByName(name);
		return ResponseEntity.ok(found);
	}

//get by wheels
	//postman pat(h) "/getVeesByWheels/{wheels}"
	@GetMapping("/getVeesByWheels/{wheels}")
	public ResponseEntity<List<Vehicle>> getVehicleByWheels(@PathVariable Integer wheels) {
		List<Vehicle> found = this.service.getVeesByWheels(wheels);
		return ResponseEntity.ok(found);
	}

//3rd - get by doors
	// postman pat(h) "/getVeesByDoors/{doors}"
	@GetMapping("/getVeesByDoors/{doors}")
	public ResponseEntity<List<Vehicle>> getVehicleByDoors(@PathVariable Integer doors) {
		List<Vehicle> found = this.service.getVeesByWheels(doors);
		return ResponseEntity.ok(found);
	}

}
