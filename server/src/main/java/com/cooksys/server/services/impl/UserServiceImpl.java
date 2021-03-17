package com.cooksys.server.services.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserEditRequestDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.ImUsedException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.CompanyMapper;
import com.cooksys.server.mappers.ProjectMapper;
import com.cooksys.server.mappers.RoleMapper;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.repositories.ProjectRepository;
import com.cooksys.server.repositories.RoleRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;
	private TeamRepository teamRepo;
	private CompanyRepository companyRepo;
	private ProjectRepository projectRepo;
	private RoleRepository roleRepo;

	private UserMapper userMap;
	private TeamMapper teamMap;
	private CompanyMapper companyMap;
	private ProjectMapper projectMap;
	private RoleMapper roleMap;

	/*
	 * GET User if user doesn't exist or is deleted, throw exception
	 */
	@Override
	public UserResponseDTO getUser(String userName) {
		Optional<User> findUser = userRepo.findByUserName(userName);

		if (findUser.isEmpty()) {
			throw new NotFoundException(String.format("User with user name: '%s' could not be found.", userName));
		}
		if(findUser.isEmpty() || findUser.get().getIsDeleted()) {
			throw new NotFoundException(String.format("User with user name: '%s' could not be found or has been deleted.", userName));

		}
		return userMap.EntityToDTO(findUser.get());
	}

	/*
	 * POST (Create User) -TODO as written a User can be on a team and on a company,
	 * make them mutually exclusive? if user entity has been deleted, reactivate and
	 * return if username exists, throw exception if user's team or company exists,
	 * add user to them else set those field to null
	 */
	@Override
	public UserResponseDTO postUser(UserCreateRequestDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userRequest.getUserName());
		if (findUser.isPresent()) {
			if (findUser.get().getIsDeleted()) {
				findUser.get().setIsDeleted(false);
				return userMap.EntityToDTO(findUser.get());
			}
			throw new ImUsedException(String.format("User name: '%s' is taken.", findUser.get().getUserName()));
		}
		User createUser = userMap.CreateDTOtoEntity(userRequest);
		Optional<Role> findRole = roleRepo.findByroleTitle(createUser.getUserRole().getRoleTitle());
		if (findRole.isPresent()) {
			createUser.setUserRole(findRole.get());
		} else {
			createUser.setUserRole(null);
		}
		createUser = userRepo.saveAndFlush(createUser);
		return userMap.EntityToDTO(createUser);
	}

	/*
	 * PATCH User (edit user info) - only a user can edit their own info a user can
	 * only edit certain information (they can not add themselves to a team, or
	 * project) if user does not exist OR deleted throw exception if user
	 * credentials do not match throw exception if changing User team or company:
	 * remove said User from previous team/company (if they exist) add said User to
	 * new team/company set the rest of the fields
	 */
	@Override
	public UserResponseDTO patchUser(String userName, UserEditRequestDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userName);
		if (findUser.isEmpty() || findUser.get().getIsDeleted()) {
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userName));
		}
		System.out.println(userRequest);
		if (!findUser.get().getUserName().equals(userRequest.getCredentials().getUserName())
				|| !findUser.get().getPassword().equals(userRequest.getCredentials().getPassword())) {
			throw new BadRequestException("Username/Password do not match.");
		}

		findUser.get().setFirstName(userRequest.getFirstName());
		findUser.get().setLastName(userRequest.getLastName());
		findUser.get().setUserName(userRequest.getUserName());
		findUser.get().setPassword(userRequest.getPassword());
		findUser.get().setUpdated(new Timestamp(System.currentTimeMillis()));
		findUser.get().setUpdatedBy(findUser.get());

		return userMap.EntityToDTO(userRepo.saveAndFlush(findUser.get()));
	}

}
