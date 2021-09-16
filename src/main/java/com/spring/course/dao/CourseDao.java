package com.spring.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.spring.course.model.Course;




@Component
public class CourseDao {
	
	@Autowired
	private JdbcTemplate template;

	public List<Course> getCourseData() {
		
		String sql = "SELECT * FROM course ";
		
		List<Course> courseList = template.query(sql,new ResultSetExtractor<List<Course>>() {
			
			@Override
			public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Course> list = new ArrayList<>();
				
				while(rs.next()) {
					Course course = new Course();
					course.setId(rs.getInt("Id"));
					course.setName(rs.getString("Name"));
					course.setAmount(rs.getString("Amount"));
					course.setField(rs.getString("Field"));
					course.setDuration(rs.getString("Duration"));
					list.add(course);
				}
				return list;
			}
		});
		
		courseList.stream().forEach(course ->{
			System.out.println(course.getId() + "-" + course.getName() + "-" + course.getAmount() + "-" + course.getField() + "-" + course.getDuration());
		});
		return courseList;
	}

	public List<Course> getCourseDataBasedOnId(int id) {
		
        String sql = "SELECT * FROM course WHERE Id = " + id;
		
		List<Course> courseList = template.query(sql,new ResultSetExtractor<List<Course>>() {
			
			@Override
			public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Course> list = new ArrayList<>();
				
				while(rs.next()) {
					Course course = new Course();
					course.setId(rs.getInt("Id"));
					course.setName(rs.getString("Name"));
					course.setAmount(rs.getString("Amount"));
					course.setField(rs.getString("Field"));
					course.setDuration(rs.getString("Duration"));
					list.add(course);
				}
				return list;
			}
		});
		
		courseList.stream().forEach(course ->{
			System.out.println(course.getId() + "-" + course.getName() + "-" + course.getAmount() + "-" + course.getField() + "-" + course.getDuration());
		});
		return courseList;
	}

	public boolean addCourseToDb(Course co) {
		
		boolean status = false;
		try {
			String sql = "INSERT INTO course(Id,Name,Amount,Field,Duration) values("+co.getId()+",'"+co.getName()+"','"+co.getAmount()+"','"+co.getField()+"','"+co.getDuration()+"')";
			template.execute(sql);
			status = true;
		}
		catch(Exception e){
			status = false;
		}
		return status;
		
	}

	public boolean delCourseFromDb(int id) {
		
		boolean status = false;
		try {
			String sql = "DELETE FROM course WHERE Id =" + id;
			template.execute(sql);
			status = true;
		}
		catch(Exception e){
			status = false;
		}
		return status;
		
	}

}
