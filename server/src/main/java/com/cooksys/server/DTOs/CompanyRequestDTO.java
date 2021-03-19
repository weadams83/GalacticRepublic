package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Company;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data


public class CompanyRequestDTO {
	
	private String companyName;
	
	private String companyDescription;


}
