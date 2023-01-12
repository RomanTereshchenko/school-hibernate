package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import com.foxminded.javaspring.schoolspringjdbc.model.Student;

public interface StudentDao {
	
	public void saveStudent(Student student);
	
	public int deleteStudentFromDB(int studentID);
	
	public void addGroupIDToStudentInDB(Student student);
	
	public List<Student> findStudentsRelatedToCourse(String courseName);

}
