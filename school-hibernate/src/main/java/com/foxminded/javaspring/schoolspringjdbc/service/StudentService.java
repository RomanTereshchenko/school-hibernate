package com.foxminded.javaspring.schoolspringjdbc.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.javaspring.schoolspringjdbc.dao.JPAStudentDao;
import com.foxminded.javaspring.schoolspringjdbc.model.Course;
import com.foxminded.javaspring.schoolspringjdbc.model.Student;
import com.foxminded.javaspring.schoolspringjdbc.utils.ScannerUtil;

@Service
public class StudentService {

	private ScannerUtil scannerUtil;
	private JPAStudentDao jpaStudentDao;

	@Autowired
	public StudentService(ScannerUtil scannerUtil, JPAStudentDao jpaStudentDao) {
		this.scannerUtil = scannerUtil;
		this.jpaStudentDao = jpaStudentDao;
	}

	public List<Student> findStudentsRelatedToCourse() {
		System.out.println("Find all students related to the course with the given name");
		System.out.println("Enter a course name from the list: ");
		for (Course course : DBGeneratorService.courses) {
			System.out.println(course.getCourseName());
		}
		String courseName = scannerUtil.scanString();
		List<Student> studentsOfCourse = jpaStudentDao.findStudentsRelatedToCourse(courseName);
		for (Student student : studentsOfCourse) {
			System.out.println(student.getFirstName() + " " + student.getLastName());
		}
		System.out.println();
		return studentsOfCourse;
	}

	public void addNewStudent() {
		System.out.println("Add a new student");
		System.out.println("Enter the student first name");
		String firstName = scannerUtil.scanString();
		System.out.println("Enter the student last name");
		String lastName = scannerUtil.scanString();
		jpaStudentDao.saveStudent(new Student(firstName, lastName));
		System.out.println("New student " + firstName + " " + lastName + " is added to School database");
		System.out.println();
	}

	public void deleteStudent() {
		System.out.println("Delete a student by the STUDENT_ID");
		System.out.println("Enter the student ID");
		int studentIdToDelete = scannerUtil.scanInt();
		jpaStudentDao.deleteStudentFromDB(studentIdToDelete);
		System.out.println("Student with ID " + studentIdToDelete + " is deleted from School database");
		System.out.println();
	}

	public void addStudentToCourse() {
		System.out.println("Add a student to the course (from a list)");
		System.out.println("Enter the student ID");
		int studentId = scannerUtil.scanInt();
		System.out.println("The available courses are:");
		for (Course course : DBGeneratorService.courses) {
			System.out.println(course.getCourseID() + " - " + course.getCourseName());
		}
		System.out.println("Enter the course ID");
		int courseId = scannerUtil.scanInt();
		Set<Course> studentCourses = DBGeneratorService.students.get(studentId - 1).getCourses();
		for (Course studentCourse : studentCourses) {
			if (studentCourse.getCourseID() == courseId) {
				System.out.println("This student is already assigned to this course. Choose other student and course.");
				return;
			}
			DBGeneratorService.students.get(studentId - 1).getCourses()
					.add(DBGeneratorService.courses.get(courseId - 1));
			jpaStudentDao.updateStudent(DBGeneratorService.students.get(studentId - 1));
			System.out.println("Course with ID " + courseId + " is assigned to student with ID " + studentId
					+ " in School database");
			return;
		}
	}

	public void removeStudentFromCourse() {
		System.out.println("Remove the student from one of their courses");
		System.out.println("Enter the student ID");
		int studentIdToRemove = scannerUtil.scanInt();
		Set<Course> studentCourses = DBGeneratorService.students.get(studentIdToRemove - 1).getCourses();
		System.out.println("This student is assigned to the following courses:");
		for (Course studentCourse : studentCourses) {
			System.out.println(studentCourse.getCourseID() + " - "
					+ DBGeneratorService.courses.get(studentCourse.getCourseID() - 1).getCourseName());
		}
		System.out.println("Enter the course ID, from which to remove this student");
		int courseIdToRemove = scannerUtil.scanInt();
		for (Course studentCourse : studentCourses) {
			if (studentCourse.getCourseID() == courseIdToRemove) {
				DBGeneratorService.students.get(studentIdToRemove - 1).getCourses()
						.remove(DBGeneratorService.courses.get(courseIdToRemove - 1));
				jpaStudentDao.updateStudent(DBGeneratorService.students.get(studentIdToRemove - 1));
				System.out.println("Student with ID " + studentIdToRemove + " is removed from the course "
						+ courseIdToRemove + " in School database");
				return;
			}
		}
		System.out.println("This course is not assigned to this student. Choose other student and course");
		return;
	}
}
