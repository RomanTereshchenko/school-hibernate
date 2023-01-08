package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import com.foxminded.javaspring.schoolspringjdbc.model.Group;

public interface GroupDao {
	
	public int addGroupToDB(Group group);
	
	public List<Group> selectGroupsByStudentsCount(int studentsCount);

}
