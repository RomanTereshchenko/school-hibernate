package com.foxminded.javaspring.schoolspringjdbc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "students")
@Table(name = "students", schema = "school")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@Column(name = "group_id", insertable = false, updatable = false)
	private int groupID;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

//	@Transient
//	public List<Course> courses;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> coursesSet = new HashSet<>();

	public Student(int studentID, String firstName, String lastName) {
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(int studentID, int groupID) {
		this.studentID = studentID;
		this.groupID = groupID;
	}
	
	public void addCourse (Course course) {
		coursesSet.add(course);
		course.getStudentsSet().add(this);
	}
	
	public void removeCourse (Course course) {
		coursesSet.remove(course);
		course.getStudentsSet().remove(this);
	}

}
