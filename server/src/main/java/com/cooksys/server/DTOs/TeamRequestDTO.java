package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class TeamRequestDTO {
	
	private Team teamName;
	
	private Team teamDescription;
	
	private Team parentCompany;

}
