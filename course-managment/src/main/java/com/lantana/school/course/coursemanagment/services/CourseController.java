package com.lantana.school.course.coursemanagment.services;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lantana.school.course.coursemanagment.security.Identity;

@RestController
public class CourseController {

    @Autowired
    private HttpServletRequest request;
	
	@Autowired
	private CourseService couseService;

	@GetMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello() {
		System.out.println("calling course method");
		return "Greetings from Spring Boot!";
	}

	@GetMapping(value = "/courses/{courseCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Course getCourse(@PathVariable("courseCode") String courseCode, Model model) throws JsonProcessingException {
		configCommonAttributes(model);
		System.out.println("calling get course method " + courseCode);
		Course course = couseService.getCourse(courseCode);
		System.out.println("Couse featched from repo " + course.name);
//		System.out.println("Permissions "+((Identity)model.asMap().get("identity")).getPermissions());
//		KeycloakPrincipal principal = (KeycloakPrincipal)request.getUserPrincipal();
//		System.out.println(principal.getKeycloakSecurityContext().getTokenString());
		return course;
	}

	private void configCommonAttributes(Model model) {
		model.addAttribute("identity", new Identity(getKeycloakSecurityContext()));
		
	}

	private KeycloakSecurityContext getKeycloakSecurityContext() {
		return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
	}

}
