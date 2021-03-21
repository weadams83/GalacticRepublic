package com.cooksys.server.services;

import java.util.List;

import com.cooksys.server.DTOs.CompanyCreateRequestDTO;
import com.cooksys.server.DTOs.CompanyRequestDTO;
import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.entities.Company;

public interface CompanyService {

	List<CompanyResponseDTO> getAllCompanies();

	CompanyResponseDTO getCompany(String companyName);

	CompanyResponseDTO postCompany(CompanyCreateRequestDTO companyRequest);

	CompanyResponseDTO updateCompanyDescription(String companyName, CompanyRequestDTO companyUpdate);
}
