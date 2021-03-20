package com.cooksys.server.DTOs;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CompanyResponseUserDTO {
	private String userName;
	
	private String firstName;

	private String lastName;
	
	private Boolean isDeleted;
	
	private RoleResponseDTO userRole;
	
	private boolean newUser;
}
