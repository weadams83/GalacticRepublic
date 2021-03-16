package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class UserDTO {
	
	private User userName;

	private User firstName;

	private User lastName;
	
	private User associatedTeam;
	
	private User userCompany;
	
	private User userProject;
	
	private User userRole;

}
