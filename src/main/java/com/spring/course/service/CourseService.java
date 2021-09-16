package com.spring.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.course.dao.CourseDao;
import com.spring.course.model.Course;


@Service
public class CourseService {
	
	@Autowired
	private CourseDao dao;

	public List<Course> getCourseData() {
		
		List<Course> coList = dao.getCourseData();
		return coList;
	}

	public Course getCourseDataBasedOnId(int id) {
		
		List<Course> coList = dao.getCourseDataBasedOnId(id);
		return coList.get(0);
	}

	
	public boolean addCourseToDb(Course co) {
		
		boolean status = dao.addCourseToDb(co);
		return status;
	}

	public boolean delCourseFromDb(int id) {
		
		boolean status = dao.delCourseFromDb(id);
		return status;
	}

}
