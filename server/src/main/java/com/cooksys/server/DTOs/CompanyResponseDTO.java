package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Company;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data


public class CompanyResponseDTO {
	
	private Company companyName;
	
	private Company companyDescription;
	
	private Company teams;
	
	private Company users;

}
