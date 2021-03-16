package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Project;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data


public class ProjectResponseDTO {
	
	private Project name;
	
	private Project description;
	
	private Project projectUsers;
	
	private Project created;
	
	private Project updated;

}