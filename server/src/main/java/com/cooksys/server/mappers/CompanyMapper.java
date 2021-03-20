package com.cooksys.server.mappers;

import org.mapstruct.Mapper;

import java.util.List;

import com.cooksys.server.DTOs.CompanyCreateRequestDTO;
import com.cooksys.server.DTOs.CompanyDTO;
import com.cooksys.server.DTOs.CompanyRequestDTO;
import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	Company CompanyDTOtoEntity(CompanyDTO companyDTO);
	
	CompanyResponseDTO entityToResponseDTO(Company company);

	List<Company> entitiesToResponseDTOs(List<Company> findAll);

	Company RequestDTOToEntity(CompanyRequestDTO companyRequest);

	CompanyRequestDTO EntityToDTO(Company createCompany);
	
	Company CompanyRequestDTOtoEntity(CompanyRequestDTO companyName);
}
