package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectResponseDTO {
	
	private String name;
	
	private String description;
	
	private UserResponseDTO user;
}
