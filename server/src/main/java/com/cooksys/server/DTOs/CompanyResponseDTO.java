package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Company;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CompanyResponseDTO {

	
	private Long id;
	
	private String companyName;

	private String companyDescription;		

	
}
