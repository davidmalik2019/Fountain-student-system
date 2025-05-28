package com.fisnig.springBootCrud.service;

import java.util.List;

import com.fisnig.springBootCrud.model.Course;

public interface CourseService {
	List<Course> getAllCourses();
	
	Course saveCourse(Course course);
	
	Course getCourseById(Long id);
	
	Course updateCourse(Course course);
	
	void deleteCourseById(Long id);
	
}

