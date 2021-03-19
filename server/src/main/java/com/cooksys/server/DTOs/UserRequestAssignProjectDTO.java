package com.cooksys.server.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestAssignProjectDTO {
	private UserSignInRequestDTO credentials;//holds the username/password of user that is editing 

	private String projectName;
}
