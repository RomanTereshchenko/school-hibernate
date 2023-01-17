package com.foxminded.javaspring.schoolspringjdbc.service;

import static java.lang.System.exit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.dao.JPACourseDao;
import com.foxminded.javaspring.schoolspringjdbc.dao.JPAGroupDao;
import com.foxminded.javaspring.schoolspringjdbc.dao.JPAStudentDao;
import com.foxminded.javaspring.schoolspringjdbc.dao.JPATablesDao;
import com.foxminded.javaspring.schoolspringjdbc.model.Course;
import com.foxminded.javaspring.schoolspringjdbc.model.Group;
import com.foxminded.javaspring.schoolspringjdbc.model.Student;
import com.foxminded.javaspring.schoolspringjdbc.utils.ScannerUtil;

@Service
public class DBGeneratorService {

	private JPATablesDao jpaTablesDao;
	private GroupGeneratorService groupGeneratorService;
	private JPAGroupDao jpaGroupDao;
	private JPACourseDao jpaCourseDao;
	private StudentGeneratorService studentGeneratorService;
	private JPAStudentDao jpaStudentDao;
	private CourseGeneratorService courseGeneratorService;
	private ScannerUtil scannerUtil;
	private int groupsNumber = 10;
	private int studentsNumber = 200;
	private int menuOptionsNumber = 7;
	private GroupService groupService;
	private StudentService studentService;
	public static List<Group> groups = new ArrayList<>();
	public static List<Course> courses = new ArrayList<>();
	public static List<Student> students = new ArrayList<>();

	@Autowired
	public DBGeneratorService(JPATablesDao jpaTablesDao, GroupGeneratorService groupGeneratorService,
			JPAGroupDao jpaGroupDao, JPACourseDao jpaCourseDao, StudentGeneratorService studentGeneratorService,
			JPAStudentDao jpaStudentDao, CourseGeneratorService courseGeneratorService, GroupService groupService,
			StudentService studentService, ScannerUtil scannerUtil) {
		this.jpaTablesDao = jpaTablesDao;
		this.groupGeneratorService = groupGeneratorService;
		this.jpaGroupDao = jpaGroupDao;
		this.jpaCourseDao = jpaCourseDao;
		this.studentGeneratorService = studentGeneratorService;
		this.jpaStudentDao = jpaStudentDao;
		this.courseGeneratorService = courseGeneratorService;
		this.groupService = groupService;
		this.studentService = studentService;
		this.scannerUtil = scannerUtil;
	}

	@Transactional
	public void startUp() {
		jpaTablesDao.truncateTables();
		groups = groupGeneratorService.generateNGroups(groupsNumber);
		jpaGroupDao.addAllGroupsToDB();
		courses = courseGeneratorService.generateCourses();
		jpaCourseDao.addAllCoursesToDB();
		students = studentGeneratorService.generateNStudents(studentsNumber);
		jpaStudentDao.addStudentsToDB();
		studentGeneratorService.assignAllGroupsToAllItsStudents();
		jpaStudentDao.updateAllStudentsInDB();
		studentGeneratorService.assignCoursesToAllStudents();
		jpaStudentDao.updateAllStudentsInDB();
	}

	public void menu() {
		String options = "1 - Find all groups with less or equal students' number \n2 - Find all students related to "
				+ "the course with the given name \n3 - Add a new student \n4 - Delete a student by the STUDENT_ID "
				+ "\n5 - Add a student to the course (from a list) \n6 - Remove the student from one of their courses "
				+ "\n7 - Exit";
		int option = 1;
		while (option != menuOptionsNumber) {
			printMenu(options);
			try {
				option = scannerUtil.scanInt();
				switch (option) {
				case 1:
					groupService.findGroupsByStudentsCount();
					break;
				case 2:
					studentService.findStudentsRelatedToCourse();
					break;
				case 3:
					studentService.addNewStudent();
					break;
				case 4:
					studentService.deleteStudent();
					break;
				case 5:
					studentService.addStudentToCourse();
					break;
				case 6:
					studentService.removeStudentFromCourse();
					break;
				default:
					exit(0);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Please, enter an integer value between 1 and " + menuOptionsNumber);
				scannerUtil.scanInt();
			}
		}
		scannerUtil.closeScan();
	}

	private void printMenu(String options) {
		System.out.println(options);
		System.out.println("Choose your option : ");
	}

}
