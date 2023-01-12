package com.foxminded.javaspring.schoolspringjdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "students_courses")
//@Table(name = "students_courses", schema = "school")
public class StudentCourse {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int studentCourseId;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "student_id")
//	private Student student;
//	
//	@Column(name = "student_id", insertable = false, updatable = false)	
//	private int studentId;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "course_id")
//	private Course course;
//	
//	@Column(name = "course_id", insertable = false, updatable = false)	
//	private int courseId;
//
//	public StudentCourse(int studentId, int courseId) {
//		super();
//		this.studentId = studentId;
//		this.courseId = courseId;
//	}

	
}
