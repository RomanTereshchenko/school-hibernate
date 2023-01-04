package com.foxminded.javaspring.schoolspringjdbc.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students_courses")
public class StudentCourse {

	@ManyToOne(fetch = FetchType.LAZY)
	int studentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	int courseId;

}
