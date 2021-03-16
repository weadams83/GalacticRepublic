package com.cooksys.server.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {

	@Id
	@GeneratedValue
	private Long projectId;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Team team;
	
	private String name;
	
	private String description;
	
	@CreationTimestamp
	private final Timestamp created = new Timestamp(System.currentTimeMillis());
	
	@UpdateTimestamp
	private Timestamp updated;

	@Column(nullable = false)
	private Boolean isDeleted = false;
	
	@OneToOne
	private User updatedBy;

	@OneToMany(mappedBy = "userProject")
	private List<User> projectUsers;

	@ManyToOne
	private Team projectTeam;

}
