package com.cooksys.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private Long id;
	
	private String Name;
	
	private String description;

}
