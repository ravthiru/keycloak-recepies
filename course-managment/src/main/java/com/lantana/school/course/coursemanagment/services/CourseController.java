package com.lantana.school.course.coursemanagment.services;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@Autowired
	private CourseService couseService;

//	@Autowired
//	private HttpServletRequest request;

	@GetMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello() {
		System.out.println("calling course method");
		return "Greetings from Spring Boot!";
	}

	@GetMapping(value = "/courses/{courseCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Course getCourse(@PathVariable("courseCode") String courseCode) {
		System.out.println("calling get course method "+ courseCode);
		Course course = couseService.getCourse(courseCode);
		System.out.println("Couse featched from repo "+course.name);
		return course;
	}

}
