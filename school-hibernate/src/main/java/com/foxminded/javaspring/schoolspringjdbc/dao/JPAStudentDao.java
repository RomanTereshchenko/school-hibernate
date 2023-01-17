package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Student;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPAStudentDao {

	@PersistenceContext
	private final EntityManager em;

	@Autowired
	public JPAStudentDao(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void addStudentsToDB() {
		DBGeneratorService.students.forEach(this::saveStudent);
		log.info("Students added to School database");
	}

	@Transactional
	public void saveStudent(Student student) {
		em.persist(student);
	}

	@Transactional
	public int deleteStudentFromDB(int studentID) {
		return em.createNativeQuery("DELETE FROM school.students s WHERE s.student_id = ?", Student.class)
				.setParameter(1, studentID).executeUpdate();
	}

	@Transactional
	public void updateAllStudentsInDB() {
		for (Student student : DBGeneratorService.students) {
			if (student.getGroupID() != 0) {
				updateStudent(student);
			}
		}
		log.info("Students updated");
	}

	@Transactional
	@Modifying
	public void updateStudent(Student student) {
		em.merge(student); 
	}

	@Transactional
	public List<Student> findStudentsRelatedToCourse(String courseName) {
		return em.createNativeQuery(
				"SELECT s.student_id, s.group_id, s.first_name, " + "s.last_name FROM school.students s "
						+ "INNER JOIN school.students_courses sc ON s.student_id = sc.student_id "
						+ "INNER JOIN school.courses c ON c.course_id = sc.course_id " + "WHERE course_name = ?",
				Student.class).setParameter(1, courseName).getResultList();
	}

}
