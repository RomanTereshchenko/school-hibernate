package com.foxminded.javaspring.schoolspringjdbc.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Course;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPACourseDao implements CourseDao {

	private final EntityManager em;

	@Autowired
	public JPACourseDao(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public void addAllCoursesToDB() {
		DBGeneratorService.courses.forEach(this::saveCourse);
		log.info("Courses added to School database");
	}

	@Override
	@Transactional
	public void saveCourse(Course course) {
		em.persist(course);
	}

}
