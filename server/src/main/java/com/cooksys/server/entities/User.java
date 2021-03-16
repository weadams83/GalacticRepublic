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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String userName;

	private String firstName;

	private String lastName;

	private String password;

	@CreationTimestamp
	private final Timestamp created = new Timestamp(System.currentTimeMillis());;
	
	@UpdateTimestamp
	private Timestamp updated;

	@Column(nullable = false)
	private Boolean isDeleted = false;

	@ManyToOne
	private Team associatedTeam;
	
	@OneToOne
	private User updatedBy;
	
	@ManyToOne
	private Company userCompany;

	@ManyToOne
	private Project userProject;

	@ManyToOne
	private Role userRole;
	
	@OneToMany (mappedBy = "user")
	private List<Project> projects;
	
	@Override
	public String toString() {
		return(String.format("id %d firstName %s", id,firstName));
	}

}
