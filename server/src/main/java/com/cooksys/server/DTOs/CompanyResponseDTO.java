package com.cooksys.server.DTOs;

import java.util.List;

import com.cooksys.server.entities.Company;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CompanyResponseDTO {
	private String companyName;

	private String companyDescription;		
	
	private List<CompanyResponseTeamDTO> teams;

	private List<CompanyResponseFilterUsersDTO> users;

}
