package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class TeamDTO {
	
	private Team teamName;
	
	private Team parentCompany;
	
	private Team teamDescription;
	
	private Team teamMembers;
	
	private Team projects;
	

}
