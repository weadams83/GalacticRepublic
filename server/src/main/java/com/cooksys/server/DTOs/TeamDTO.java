package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Company;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TeamDTO {
	private Company parentCompany;
	
	private String teamName;

	private String teamDescription;
}
