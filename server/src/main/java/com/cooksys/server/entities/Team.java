package com.cooksys.server.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	private Long companyId;
	
	private String name;
	
	private String description;
	
	@Column(nullable = false)
	private Boolean isDeleted = false;

}
