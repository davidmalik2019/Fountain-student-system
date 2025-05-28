package com.fisnig.springBootCrud.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fisnig.springBootCrud.model.Course;
import com.fisnig.springBootCrud.repository.CourseRepository;
import com.fisnig.springBootCrud.service.CourseService;


@Service
public class CourseServiceImpl implements CourseService{

	private CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return (List<Course>) courseRepository.findAll();
	}

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	public Course getCourseById(Long id) {
		return courseRepository.findById(id).get();
	}

	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}

	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);	
	}

}
