package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Project;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectRequestDTO {
	
	private Project projectName;
	
	private Project description;
	
	private Project user;
	
	private Project team;
	
	private Project projectTeam;
	

	
	

}
