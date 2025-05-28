package com.fisnig.springBootCrud.controller;

import com.fisnig.springBootCrud.model.Course;
import com.fisnig.springBootCrud.model.Teacher;
import com.fisnig.springBootCrud.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

   
    
    @GetMapping("/courses")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}

    @GetMapping("/courses/new")
	public String createcourse(Model model) {
		
		// create student object to hold student form data
		Course course = new Course();
		model.addAttribute("course", course);
		return "create_course";
		
	}
  

	@PostMapping("/courses")
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.saveCourse(course);
		return "redirect:/courses";
	}
	
    

    
    
    @PostMapping("/courses/{id}")
	public String updateCourse(@PathVariable Long id,
			@ModelAttribute("course") Course course,
			Model model) {
		
		// get student from database by id
		Course existingCourse = courseService.getCourseById(id);
		existingCourse.setId(id);
		existingCourse.setCourseCode(course.getCourseCode());
		existingCourse.setCourseName(course.getCourseName());
		existingCourse.setAmount(course.getAmount());
		existingCourse.setDuration(course.getDuration());
		
		
		// save updated student object
		courseService.updateCourse(existingCourse);
		return "redirect:/courses";		
	}
    

    
    @GetMapping("/courses/edit/{id}")
	public String editCourseForm(@PathVariable Long id, Model model) {
		model.addAttribute("course", courseService.getCourseById(id));
		return "edit_course";
	}

    
    
	@GetMapping("/courses/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseService.deleteCourseById(id);
		return "redirect:/courses";
	}
}
