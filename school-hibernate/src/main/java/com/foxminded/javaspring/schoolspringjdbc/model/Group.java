package com.foxminded.javaspring.schoolspringjdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
@Table(name = "groups", schema = "school")
public class Group {

	@Id
	@Column(name = "group_id")
	private int groupID;

	@Column(name = "group_name")
	private String groupName;

}
