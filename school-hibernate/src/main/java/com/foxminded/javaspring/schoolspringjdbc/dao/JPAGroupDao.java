package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Group;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPAGroupDao implements GroupDao {

	private final EntityManager em;

	@Autowired
	public JPAGroupDao(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void addAllGroupsToDB() {
		DBGeneratorService.groups.forEach(this::addGroupToDB);
		log.info("Groups added to School database");
	}

	@Override
	public void addGroupToDB(Group group) {
		Group persistingGroup = new Group();
		persistingGroup.setGroupName(group.getGroupName());
		em.persist(persistingGroup);
	}

	@Override
	public List<Group> selectGroupsByStudentsCount(int studentsCount) {
		return em
				.createNativeQuery("SELECT g.group_id AS groupID, g.group_name AS groupName FROM "
						+ "school.groups g INNER JOIN school.students s ON g.group_id = s.group_id "
						+ "GROUP BY g.group_id HAVING COUNT (g.group_id) <= ?", Group.class)
				.setParameter(1, studentsCount).getResultList();
	}

}
