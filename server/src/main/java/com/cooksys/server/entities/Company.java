package com.cooksys.server.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Company {

	@Id
	@GeneratedValue
	private Long companyId;

	private String companyName;

	private String companyDescription;

	@OneToMany(mappedBy = "parentCompany")
	private List<Team> teams;

	@OneToMany(mappedBy = "userCompany")
	private List<User> users;
	
	@Override
	public String toString() {
		return(String.format("id %d company name %s", companyId,companyName));
	}

}
