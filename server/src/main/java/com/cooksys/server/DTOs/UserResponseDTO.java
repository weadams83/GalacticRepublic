package com.cooksys.server.DTOs;

import java.util.List;

import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDTO {
	private String userName;
	
	private String firstName;

	private String lastName;
	
//	private TeamDTO associatedTeam; //TODO- use dto's to pretty these up and filter what's returned?
//	
//	private Company userCompany;
//	
//	private List<Project> projects;
//	
//	private Role userRole;
}
