package com.spring.course.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.model.Course;
import com.spring.course.service.CourseService;


@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@RequestMapping(value="/courseinfo", method=RequestMethod.GET)
	public List<Course> getCourseInfo(){
		
		List<Course> li = service.getCourseData();
		
		return li;
	}
	
	@RequestMapping(value = "/postCourseData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Course coursePostCall(@RequestBody Course course) {
		
		//validation
				if(Objects.isNull(course.getId()) || (course.getId()==0)) {
					throw new IllegalArgumentException("Course id is a mandatory field or invalid argument passed");
				}
				
				//service
				
				Course courseObj = service.getCourseDataBasedOnId(course.getId());
				
				return courseObj;
	}
	
	@RequestMapping(value = "/putCourseData", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String putCourseData(@RequestBody Course co) {
		
        boolean status = service.addCourseToDb(co);
		
		if(status) {
			return "Course added successfully";
		}
		else {
			return "Course update failed";
		}
	}
	
	@RequestMapping(value = "/delCourseData/{id}", method = RequestMethod.DELETE)
	public String delCourseData(@PathVariable int id) {
		
        boolean status = service.delCourseFromDb(id);
		
		if(status) {
			return "Course deleted successfully";
		}
		else {
			return "Course deletion failed";
		}
	}
	
}
