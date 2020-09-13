package com.github.ravthiru.keycloak.model;

import org.keycloak.adapters.AuthenticatedActionsHandler;

public class Employee {

	AuthenticatedActionsHandler ff;
	
	private final String id;

	private final String name;

	public Employee(String id, String name) {
		this.id = id;
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

}
