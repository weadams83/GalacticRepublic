package com.cooksys.server.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Company company;

	private String teamName;

	private String teamDescription;

	@Column(nullable = false)
	private Boolean isDeleted = false;

	@OneToMany(mappedBy = "associatedTeam")
	private List<User> teamMembers;

	@OneToMany(mappedBy = "projectTeam")
	private List<Project> teamProjects;

}
