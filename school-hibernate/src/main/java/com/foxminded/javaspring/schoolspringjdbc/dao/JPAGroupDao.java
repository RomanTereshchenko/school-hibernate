package com.foxminded.javaspring.schoolspringjdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.javaspring.schoolspringjdbc.model.Group;
import com.foxminded.javaspring.schoolspringjdbc.service.DBGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JPAGroupDao implements GroupDao {

	@PersistenceContext
	private final EntityManager em;

	@Autowired
	public JPAGroupDao(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void addAllGroupsToDB() {
		DBGeneratorService.groups.forEach(this::saveGroup);
		log.info("Groups added to School database");
	}

	@Override
	@Transactional
	public void saveGroup(Group group) {
		em.persist(group);
	}

	@Override
	@Transactional
	public List<Group> selectGroupsByStudentsCount(int studentsCount) {
		return em
				.createNativeQuery("SELECT g.group_id, g.group_name FROM "
						+ "school.groups g INNER JOIN school.students s ON g.group_id = s.group_id "
						+ "GROUP BY g.group_id HAVING COUNT (g.group_id) <= ?", Group.class)
				.setParameter(1, studentsCount).getResultList();
	}

}
