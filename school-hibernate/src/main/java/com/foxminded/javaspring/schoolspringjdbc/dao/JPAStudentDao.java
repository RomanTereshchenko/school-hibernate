package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Student;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPAStudentDao implements StudentDao {

	private EntityManager em;

	@Autowired
	public JPAStudentDao(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void addStudentsToDB() {
		DBGeneratorService.students.forEach(this::addStudentToDB);
		log.info("Students added to School database");
	}

	@Override
	public int addStudentToDB(Student student) {
		return em.createNativeQuery("INSERT INTO school.students (first_name, last_name) VALUES (?, ?);", Student.class)
				.setParameter(1, student.getFirstName()).setParameter(2, student.getLastName()).executeUpdate();
	}

	@Override
	public int deleteStudentFromDB(int studentID) {
		return em.createQuery("DELETE FROM school.students s WHERE s.student_id AS studentID = ?", Student.class)
				.setParameter(1, studentID).executeUpdate();
	}

	@Transactional
	public void addGroupIDToAllTheirStudentsInDB() {
		for (Student student : DBGeneratorService.students) {
			if (student.getGroupID() != 0) {
				addGroupIDToStudentInDB(student);
			}
		}
		log.info("Students assigned to groups in School database");
	}

	@Override
	public int addGroupIDToStudentInDB(Student student) {
		return em.createNativeQuery("UPDATE school.students SET group_id = ? WHERE student_id = ?")
				.setParameter(1, student.getGroupID()).setParameter(2, student.getStudentID()).executeUpdate();
	}

	@Override
	public List<Student> findStudentsRelatedToCourse(String courseName) {
		return em.createQuery(
				"SELECT s.student_id AS studentID, s.first_name AS firstName, "
						+ "s.last_name AS lastName FROM school.students s "
						+ "INNER JOIN school.students_courses sc ON s.student_id = sc.student_id "
						+ "INNER JOIN school.courses c ON c.course_id = sc.course_id " + "WHERE course_name = ?",
				Student.class).setParameter(1, courseName).getResultList();
	}
}
