package com.cooksys.server.DTOs;

import java.util.List;

import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class TeamResponseDTO {
	private String teamName;

	private String teamDescription;

	private Company parentCompany;
	private List<User> teamMembers;
	private List<Project> projects;
}
