package com.cooksys.server.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {

	@Id
	@GeneratedValue
	private Long id;

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
	
	/*
	 * equals() compares the database entries by id and the objects 
	 * in memory in Java
	 */
	@Override
	public boolean equals(Object obj) {
		
		//check null and class
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		//compare pointers
		if (obj == this) {
			return true;
		}
		
		//compare id's
		return ((Project) obj).getId() == this.getId();
	}

	@Override
	public int hashCode() {
		/* Simplified hash. Use id field instead of username,
		 * since username can change in the database.
		 */
		return Objects.hash(this.id);
	}

}
