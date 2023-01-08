package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import com.foxminded.javaspring.schoolspringjdbc.model.StudentCourse;

public interface StudentsCoursesDao {
	
	public int addStudentCourseAssignmentInDB(StudentCourse studentCourse);
	
	public List<StudentCourse> getCoursesOfStudent(int studentID);
	
	public int deleteStudentFromCourse(int studentID, int courseID);	

}
