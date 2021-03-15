package com.cooksys.server.entities;

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
public class Role {

	@Id
	@GeneratedValue
	private Long roleId;

	@OneToMany(mappedBy = "userRole")
	private String roleTitle;

}
