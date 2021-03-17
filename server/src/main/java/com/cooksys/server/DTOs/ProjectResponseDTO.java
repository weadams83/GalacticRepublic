package com.cooksys.server.DTOs;

import java.util.List;

import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectResponseDTO {
	
	private Long id;
	
	private String projectName;
	
	private String description;
	
	private Project created;
	
	private Project updated;
	
	private List<User> projectUsers;
	
	private Team projectTeam;

}
