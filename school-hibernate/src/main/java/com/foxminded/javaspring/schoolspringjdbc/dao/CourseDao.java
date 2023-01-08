package com.foxminded.javaspring.schoolspringjdbc.dao;

import com.foxminded.javaspring.schoolspringjdbc.model.Course;

public interface CourseDao {
	
	public void addAllCoursesToDB();
	
	public int addCourseToDB(Course course);

}
