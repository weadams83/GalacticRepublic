package com.cooksys.server.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TeamEditRequestDTO {
	
	private UserSignInRequestDTO credentials;
	
	private TeamRequestDTO changes;

}
