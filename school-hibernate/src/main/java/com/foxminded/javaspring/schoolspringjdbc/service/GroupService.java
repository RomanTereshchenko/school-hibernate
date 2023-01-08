package com.foxminded.javaspring.schoolspringjdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.javaspring.schoolspringjdbc.dao.JPAGroupDao;
import com.foxminded.javaspring.schoolspringjdbc.model.Group;
import com.foxminded.javaspring.schoolspringjdbc.utils.ScannerUtil;

@Service
public class GroupService {

	private ScannerUtil scannerUtil;
	private JPAGroupDao jdbcGroupDao;

	@Autowired
	public GroupService(ScannerUtil scannerUtil, JPAGroupDao jdbcGroupDao) {
		this.scannerUtil = scannerUtil;
		this.jdbcGroupDao = jdbcGroupDao;
	}

	public List<Group> findGroupsByStudentsCount() {
		System.out.println("Find all groups with less or equal students� number: \n Enter a number between 10 and 30");
		int lessOrEqualNum = scannerUtil.scanInt();
		List<Group> selectedGroups = jdbcGroupDao.selectGroupsByStudentsCount(lessOrEqualNum);
		for (Group group : selectedGroups) {
			System.out.println(group.getGroupName());
		}
		System.out.println();
		return selectedGroups;
	}

}
