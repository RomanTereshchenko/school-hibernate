package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Course;
import com.foxminded.javaspring.schoolspringjdbc.model.StudentCourse;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPAStudentsCoursesDao  {

	private EntityManager em;

	@Autowired
	public JPAStudentsCoursesDao(EntityManager em) {
		this.em = em;
	}

//	@Transactional
//	public void addStudentsCoursesAssignmentsToDB() {
//		DBGeneratorService.students.forEach(student -> addOneStudentCoursesAssignmentsToDB(student.getStudentID()));
//		log.info("Students' assignments to courses added to School database");
//	}
//
//	void addOneStudentCoursesAssignmentsToDB(int studentID) {
//		List<Course> coursesOfStudent = DBGeneratorService.students.get(studentID - 1).getCourses();
//		for (Course course : coursesOfStudent) {
//			addStudentCourseAssignmentInDB(new StudentCourse(studentID, course.getCourseID()));
//		}
//	}
//
//	public int addStudentCourseAssignmentInDB(StudentCourse studentCourse) {
//		return em
//				.createNativeQuery("INSERT INTO school.students_courses (student_id, course_id) VALUES (?, ?);",
//						StudentCourse.class)
//				.setParameter(1, studentCourse.getStudentId()).setParameter(2, studentCourse.getCourseId())
//				.executeUpdate();
//	}
//
//	
//
//	public int deleteStudentFromCourse(int studentID, int courseID) {
//		return em.createNativeQuery("DELETE FROM school.students_courses WHERE student_id = ? AND course_id = ?;",
//				StudentCourse.class).setParameter(1, studentID).setParameter(2, courseID).executeUpdate();
//	}

}
