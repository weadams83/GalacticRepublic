package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class RoleDTO {
	
	private Role users;
	
	private Role roleTitle;

}
