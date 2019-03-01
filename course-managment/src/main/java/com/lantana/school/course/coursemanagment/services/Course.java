package com.lantana.school.course.coursemanagment.services;

public class Course {
	
	private static long nextID = 1000;
  

	public Course(String code, String name, String modules, String enrollmentTerm) {
		super();
		this.id = nextID++;
		this.code = code;
		this.name = name;
		this.modules = modules;
		this.enrollmentTerm = enrollmentTerm;
	}

	Long id;
	String code;
	String name;
	String modules;
	String enrollmentTerm;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getEnrollmentTerm() {
		return enrollmentTerm;
	}

	public void setEnrollmentTerm(String enrollmentTerm) {
		this.enrollmentTerm = enrollmentTerm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
