package com.lantana.school.course.coursemanagment.controller;

import java.net.URI;


import com.lantana.school.course.coursemanagment.services.CourseService;
import com.lantana.school.course.coursemanagment.model.Course;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class CourseController {

    @Autowired
    private CourseService couseService;

    @GetMapping(value = "/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course getCourse(@PathVariable("id") long id) throws JsonProcessingException {
        Course course = couseService.getCourse(id);
        return course;
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = couseService.addCourse(course);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCourse.getCode()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/courses/{id}")
    public void deleteStudent(@PathVariable long id) {
        System.out.println("calling delete operation");
        couseService.deleteById(id);
    }

}
