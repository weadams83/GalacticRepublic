package com.cooksys.server.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.CompanyRequestDTO;
import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.ImUsedException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.CompanyMapper;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.CompanyService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	private CompanyRepository companyRepo;
	private CompanyMapper companyMap;
	
	/*
	 * GET All Companies 
	 * returns all company information
	 */
	
	@Override
	public List<Company> getAllCompanies() {
		return companyMap.entitiesToResponseDTOs(companyRepo.findAll());
	}
	
	/*
	 * GET Company 
	 * if Company doesn't exist, notify User
	 */
	
	@Override
	public CompanyResponseDTO getCompany(String companyName) {
		Optional<Company> findCompany = companyRepo.findByCompanyName(companyName);
		if (findCompany.isEmpty()) {
			throw new NotFoundException(String.format("Company with company name: '%s' could not be found.", companyName));
		}
		return companyMap.entityToResponseDTO(findCompany.get());
	}
	
	/*
	 * POST (Create Company)
	 * if Company name exists, notify user
	 */

	@Override
	public CompanyRequestDTO postCompany(CompanyRequestDTO companyRequest) {
		Optional<Company> findCompany = companyRepo.findByCompanyName(companyRequest.getCompanyName());
		if(findCompany.isPresent()) {
			throw new ImUsedException(String.format("Company with company name: '%s' already exsist.", findCompany.get().getCompanyName()));
		}
		Company createCompany = companyMap.RequestDTOToEntity(companyRequest);
		
		companyRepo.saveAndFlush(createCompany);
		return companyMap.EntityToDTO(createCompany);
		
	}

	@Override
	public CompanyRequestDTO updateCompanyDescription(String companyName, CompanyRequestDTO companyUpdate) {
		Optional<Company> findCompany = companyRepo.findByCompanyName(companyName);
		if (findCompany.isEmpty()) {
			throw new NotFoundException(String.format("Company with company name: '%s' could not be found.", companyName));
		}
		findCompany.get().setCompanyDescription(companyUpdate.getCompanyDescription());
		companyRepo.saveAndFlush(findCompany.get());
		return companyMap.EntityToDTO(findCompany.get());
	}




	

}
