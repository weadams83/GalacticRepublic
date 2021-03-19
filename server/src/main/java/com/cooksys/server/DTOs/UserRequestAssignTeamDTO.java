package com.cooksys.server.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestAssignTeamDTO {
	private UserSignInRequestDTO credentials;//holds the username/password of user that is editing 

	private String teamName;
}
