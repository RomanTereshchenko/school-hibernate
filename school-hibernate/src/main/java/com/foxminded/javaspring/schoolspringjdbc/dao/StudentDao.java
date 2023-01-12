package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import com.foxminded.javaspring.schoolspringjdbc.model.Student;

public interface StudentDao {
	
	public void saveStudent(Student student);
	
	public int deleteStudentFromDB(int studentID);
	
	public int addGroupIDToStudentInDB(Student student);
	
	public List<Student> findStudentsRelatedToCourse(String courseName);

}
