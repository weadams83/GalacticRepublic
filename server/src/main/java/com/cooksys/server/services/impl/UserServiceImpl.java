package com.cooksys.server.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.ImUsedException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.CompanyMapper;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private UserRepository userRepo;
	private TeamRepository teamRepo;
	private CompanyRepository companyRepo;

	private UserMapper userMap;
	private TeamMapper teamMap;
	private CompanyMapper companyMap;

	/*
	 * GET User 
	 * if user doesn't exist or is deleted, notify User
	 */
	@Override
	public UserResponseDTO getUser(String userName) {
		Optional<User> findUser = userRepo.findByUserName(userName);
		if(findUser.isEmpty() || findUser.get().getIsDeleted()) {
			throw new NotFoundException(String.format("User with user name: '%s' could not be found or has been deleted.", userName));
		}
		return userMap.EntityToDTO(findUser.get());
	}

	/*
	 * POST (Create User)
	 * if user entity has been deleted, reactivate and return
	 * if username exists, notify user
	 * if user's team or company exists, add user to them
	 * else set those field to null
	 */
	@Override
	public UserResponseDTO postUser(UserCreateRequestDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userRequest.getUserName());
		if(findUser.isPresent()){
			if(findUser.get().getIsDeleted()) {
				findUser.get().setIsDeleted(false);
				return userMap.EntityToDTO(findUser.get());
			}
			throw new ImUsedException(String.format("User name: '%s' is taken.", findUser.get().getUserName()));
		}
		User createUser = userMap.CreateDTOtoEntity(userRequest);
		Optional<Team> findTeam = teamRepo.findByteamName(createUser.getAssociatedTeam().getTeamName());
		Optional<Company> findCompany = companyRepo.findBycompanyName(createUser.getUserCompany().getCompanyName());

		if(findTeam.isPresent()) {
			findTeam.get().getTeamMembers().add(createUser);
			teamRepo.saveAndFlush(findTeam.get());
			createUser.setAssociatedTeam(findTeam.get());
		}else {
			createUser.setAssociatedTeam(null);
		}
		
		if(findCompany.isPresent()) {
			findCompany.get().getUsers().add(createUser);
			companyRepo.saveAndFlush(findCompany.get());
			createUser.setUserCompany(findCompany.get());
		}else {
			createUser.setUserCompany(null);
		}

		userRepo.saveAndFlush(createUser);
		return userMap.EntityToDTO(createUser);
	}

}
