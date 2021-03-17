package com.cooksys.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.server.DTOs.CompanyRequestDTO;
import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyResponseDTO entityToResponseDTO(Company company);

	List<Company> entitiesToResponseDTOs(List<Company> findAll);

	Company EntityToRequestDTO(CompanyRequestDTO companyRequest);

	CompanyRequestDTO EntityToDTO(Company createCompany);

	CompanyRequestDTO EntityToDTO(CompanyResponseDTO companyToUpdate);
	
	Company CompanyRequestDTOtoEntity(CompanyRequestDTO companyName);

//	CompanyResponseDTO EntityToDTO(Company company);

//	Company EntityToDTO(CompanyRequestDTO companyRequest);

}
