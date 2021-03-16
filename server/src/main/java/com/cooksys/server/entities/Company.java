package com.cooksys.server.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Company {

	@Id
	@GeneratedValue
	private Long id;

	private String companyName;

	private String companyDescription;

	@OneToMany(mappedBy = "parentCompany")
	private List<Team> teams;

	@OneToMany(mappedBy = "userCompany")
	private List<User> users;
	
	@Override
	public String toString() {
		return(String.format("id %d company name %s", id,companyName));
	}
	
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
		return ((Company) obj).getId() == this.getId();
	}

	@Override
	public int hashCode() {
		/* Simplified hash. Use id field instead of username,
		 * since username can change in the database.
		 */
		return Objects.hash(this.id);
	}
}
