package com.lantana.school.course.coursemanagment.services;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lantana.school.course.coursemanagment.security.Identity;

@RestController
public class CourseController {

    @Autowired
    private HttpServletRequest request;
	
	@Autowired
	private CourseService couseService;

//	@GetMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
//	public String hello() {
//		System.out.println("calling course method");
//		return "Greetings from Spring Boot!";
//	}

	@GetMapping(value = "/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Course getCourse(@PathVariable("id") long id, Model model) throws JsonProcessingException {
		configCommonAttributes(model);
		System.out.println("calling get course method " + id);
		Course course = couseService.getCourse(id);
		System.out.println("Couse featched from repo " + course.name);
//		System.out.println("Permissions "+((Identity)model.asMap().get("identity")).getPermissions());
//		KeycloakPrincipal principal = (KeycloakPrincipal)request.getUserPrincipal();
//		System.out.println(principal.getKeycloakSecurityContext().getTokenString());
		return course;
	}
	
	
	@PostMapping("/courses")
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course savedCourse = couseService.addCourse(course);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCourse.getCode()).toUri();

		return ResponseEntity.created(location).build();

	}
	
//	@PutMapping("/students/{id}")
//	public ResponseEntity<Object> updateStudent(@PathVariable long id, @RequestBody Student student) {
//
//		Optional<Student> studentOptional = studentRepository.findById(id);
//
//		if (!studentOptional.isPresent())
//			return ResponseEntity.notFound().build();
//
//		student.setId(id);
//		
//		studentRepository.save(student);
//
//		return ResponseEntity.noContent().build();
//	}
	
	@DeleteMapping("/courses/{id}")
	public void deleteStudent(@PathVariable long id) {
		System.out.println("calling delete operation");
		couseService.deleteById(id);
	}
	
	
//	public Course updateCouse(@PathVariable("id") long id, Course updateCourse, Model model) {
//		
//		configCommonAttributes(model);
//		System.out.println("calling update course " + updateCourse.getCode());
//		Course course = couseService.getCourse(updateCourse.getCode());
//		return course;
//	}
	

	private void configCommonAttributes(Model model) {
		model.addAttribute("identity", new Identity(getKeycloakSecurityContext()));
		
	}

	private KeycloakSecurityContext getKeycloakSecurityContext() {
		return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
	}

}
