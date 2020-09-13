package com.github.ravthiru.keycloak.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.ravthiru.keycloak.model.Employee;


@RestController
public class EmployeeController {

	@GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@RolesAllowed("user")
	public Employee getEmployee(@PathVariable String id) {
		return new Employee(id, "John Newman");
	}
}
