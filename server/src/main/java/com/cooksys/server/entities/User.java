package com.cooksys.server.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class User {

	@Id
	@GeneratedValue
	@OneToOne
	private Long id;

	private String userName;

	private String firstName;

	private String lastName;

	private String password;

	@CreationTimestamp
	private Timestamp created;

	@UpdateTimestamp
	private Timestamp updated;

	@Column(nullable = false)
	private Boolean isDeleted = false;

	@ManyToOne
	private Team associatedTeam;

	@OneToOne(mappedBy = "id") // This can't be right
	private Long updatedBy;

	@ManyToOne
	private Company userCompany;

	@ManyToOne
	private Project userProject;

	@ManyToOne
	private Role userRole;

}
