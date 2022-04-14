package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.client.RequestMatcher;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultMatcher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:vehicle-schema.sql",
		"classpath:vehicle-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class VehicleControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Vehicle testVehicle = new Vehicle(null, "car", 4, 4);
		String testVehicleAsJSON = this.mapper.writeValueAsString(testVehicle);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testVehicleAsJSON);

		Vehicle testCreatedVehicle = new Vehicle(3, "car", 4, 4);
		String testCreatedVehicleAsJSON = this.mapper.writeValueAsString(testCreatedVehicle);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedVehicleAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	// getall
	@Test
	void testGetAllTest() throws Exception {

		RequestBuilder req = get("/getAll");

		List<Vehicle> testVehicleList = List.of(new Vehicle(1, "car", 4, 4), new Vehicle(2, "bike", 3, 100));
		String json = this.mapper.writeValueAsString(testVehicleList);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	// get by id test
	@Test
	void getByIdTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String json = this.mapper.writeValueAsString(new Vehicle(1, "car", 4, 4));

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	// get by name
	@Test
	void getByNameTest() throws Exception {
		RequestBuilder req = get("/getByName/car");

		List<Vehicle> testVehicleName = List.of(new Vehicle(1, "car", 4, 4));
		String json = this.mapper.writeValueAsString(testVehicleName);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void getByWheelsTest() throws Exception {
		RequestBuilder req = get("/getVeesByWheels/4");

		List<Vehicle> testVehicleWheels = List.of(new Vehicle(1, "car", 4, 4));
		String json = this.mapper.writeValueAsString(testVehicleWheels);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void getByDoorsTest() throws Exception {
		RequestBuilder req = get("/getVeesByDoors/4");

		List<Vehicle> testVehicleDoors = List.of(new Vehicle(1, "car", 4, 4));
		String json = this.mapper.writeValueAsString(testVehicleDoors);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReplace() throws Exception {
		Vehicle testVehicle = new Vehicle(null, "car", 4, 4);
		String testVehicleAsJSON = this.mapper.writeValueAsString(testVehicle);

		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testVehicleAsJSON);

		Vehicle testUpdatedVehicle = new Vehicle(1, "car", 4, 4);
		String testUpdatedVehicleAsJSON = this.mapper.writeValueAsString(testUpdatedVehicle);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedVehicleAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}
}
