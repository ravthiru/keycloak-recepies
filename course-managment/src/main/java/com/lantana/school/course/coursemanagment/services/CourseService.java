package com.lantana.school.course.coursemanagment.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CourseService {

	public static final Map<String, Course> courseMap = new LinkedHashMap<String, Course>();

	static {
		Course cs2001 = new Course("CS2001", "Mathematical Foundations of Computing", "introduction", "term1");
		Course cs2002 = new Course("CS2002", "Computer Organization and Systems", "introduction", "term1");
		Course cs2003 = new Course("CS2003", "Data Management and Data Systems", "introduction", "term2");
		Course cs2004 = new Course("CS2004", "Introduction to Computer Graphics and Imaging", "introduction", "term3");
		Course cs2005 = new Course("CS2005", "Design and Analysis of Algorithms", "introduction", "term4");
		Course cs2006 = new Course("CS2006", "Analysis of Networks", "introduction", "term4");
		courseMap.put("CS2001", cs2001);
		courseMap.put("CS2002", cs2002);
		courseMap.put("CS2003", cs2003);
		courseMap.put("CS2004", cs2004);
		courseMap.put("CS2005", cs2005);
		courseMap.put("CS2006", cs2006);
	}

	public Course getCourse(String courseCode) {
		return courseMap.get(courseCode);
	}

}
