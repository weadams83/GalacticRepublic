package com.cooksys.server.services;

import java.util.List;

import com.cooksys.server.DTOs.CompanyCreateRequestDTO;
import com.cooksys.server.DTOs.CompanyRequestDTO;
import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.entities.Company;

public interface CompanyService {

	List<Company> getAllCompanies();

	CompanyResponseDTO getCompany(String companyName);

	CompanyRequestDTO postCompany(CompanyCreateRequestDTO companyRequest);

	CompanyRequestDTO updateCompanyDescription(String companyName, CompanyRequestDTO companyUpdate);
}
