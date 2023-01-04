package com.foxminded.javaspring.schoolspringjdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "courses")
public class Course {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int courseID;
	
	@Column(name = "COUSE_NAME")
	private String courseName;
	
	@Column(name = "COURSE_DESCRIPTION")
	private String courseDescription;
	
	public Course(int courseID, String courseName) {
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
}
