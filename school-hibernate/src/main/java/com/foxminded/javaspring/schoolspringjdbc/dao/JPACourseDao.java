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

	@Transactional
	@Override
	public void addAllCoursesToDB() {
		DBGeneratorService.courses.forEach(this::addCourseToDB);
		log.info("Courses added to School database");
	}

	@Override
	public int addCourseToDB(Course course) {
		return em.createNativeQuery("INSERT INTO school.courses(course_name) VALUES (?);", Course.class)
		.setParameter(1, course.getCourseName()).executeUpdate();

	}

}
