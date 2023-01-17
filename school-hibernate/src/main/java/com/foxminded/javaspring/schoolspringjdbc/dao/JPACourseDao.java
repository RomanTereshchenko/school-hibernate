package com.foxminded.javaspring.schoolspringjdbc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Course;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPACourseDao {

	@PersistenceContext
	private final EntityManager em;

	@Autowired
	public JPACourseDao(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void addAllCoursesToDB() {
		DBGeneratorService.courses.forEach(this::saveCourse);
		log.info("Courses added to School database");
	}

	@Transactional
	public void saveCourse(Course course) {
		em.persist(course);
	}

}
