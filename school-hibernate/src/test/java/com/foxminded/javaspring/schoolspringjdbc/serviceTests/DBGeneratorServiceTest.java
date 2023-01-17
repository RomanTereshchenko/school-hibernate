package com.foxminded.javaspring.schoolspringjdbc.serviceTests;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.javaspring.schoolspringjdbc.dao.JPACourseDao;
import com.foxminded.javaspring.schoolspringjdbc.dao.JPAGroupDao;
import com.foxminded.javaspring.schoolspringjdbc.dao.JPAStudentDao;
import com.foxminded.javaspring.schoolspringjdbc.dao.JPATablesDao;
import com.foxminded.javaspring.schoolspringjdbc.service.CourseGeneratorService;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;
import com.foxminded.javaspring.schoolspringjdbc.service.GroupGeneratorService;
import com.foxminded.javaspring.schoolspringjdbc.service.StudentGeneratorService;

@ExtendWith (MockitoExtension.class)
class DBGeneratorServiceTest {
	
	@Mock
	private JPATablesDao jpaTablesDao;
	@Mock
	private GroupGeneratorService groupGeneratorService;
	@Mock
	private JPAGroupDao jpaGroupDao;
	@Mock
	private CourseGeneratorService courseGeneratorService;
	@Mock
	private JPACourseDao jpaCourseDao;
	@Mock
	private StudentGeneratorService studentGeneratorService;
	@Mock
	private JPAStudentDao jpaStudentDao;
	
	@InjectMocks
	private DBGeneratorService dbGeneratorService;
	
	@Test
	void testStartUp() {
		
		jpaTablesDao.truncateTables();
		groupGeneratorService.generateNGroups(10);
		jpaGroupDao.addAllGroupsToDB();
		courseGeneratorService.generateCourses();
		jpaCourseDao.addAllCoursesToDB();
		studentGeneratorService.generateNStudents(20);
		jpaStudentDao.addStudentsToDB();
		studentGeneratorService.assignAllGroupsToAllItsStudents();
		jpaStudentDao.updateAllStudentsInDB();
		studentGeneratorService.assignCoursesToAllStudents();
		verify(jpaTablesDao).truncateTables();
		verify(groupGeneratorService).generateNGroups(anyInt());
		verify(jpaGroupDao).addAllGroupsToDB();
		verify(courseGeneratorService).generateCourses();
		verify(jpaCourseDao).addAllCoursesToDB();
		verify(studentGeneratorService).generateNStudents(anyInt());
		verify(jpaStudentDao).addStudentsToDB();
		verify(studentGeneratorService).assignAllGroupsToAllItsStudents();
		verify(jpaStudentDao).updateAllStudentsInDB();
		verify(studentGeneratorService).assignCoursesToAllStudents();
	}
	
}
