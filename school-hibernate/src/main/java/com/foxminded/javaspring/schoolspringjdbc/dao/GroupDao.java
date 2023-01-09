package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import com.foxminded.javaspring.schoolspringjdbc.model.Group;

public interface GroupDao {
	
	public void addGroupToDB(Group group);
	
	public List<Group> selectGroupsByStudentsCount(int studentsCount);

}
