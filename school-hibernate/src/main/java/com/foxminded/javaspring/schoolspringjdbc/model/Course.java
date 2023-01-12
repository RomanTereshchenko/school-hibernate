package com.foxminded.javaspring.schoolspringjdbc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "courses")
@Table (name = "courses", schema = "school")
public class Course {
	
	@Id
	@Column (name = "course_id")
	private int courseID;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "course_description")
	private String courseDescription;
	
	@ManyToMany(mappedBy = "coursesSet")
	private Set<Student> studentsSet = new HashSet<>();
	
	public Course(int courseID, String courseName) {
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
}
