package com.cooksys.server.services.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserEditRequestDTO;
import com.cooksys.server.DTOs.UserRequestAssignCompanyDTO;
import com.cooksys.server.DTOs.UserRequestAssignProjectDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.DTOs.UserSignInRequestDTO;
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
	 * POST (Create User)
	 * if user entity has been deleted, reactivate and return
	 * if username exists, throw exception
	 * if user role exists, set that user's role, else set to null
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
	 * PATCH User (edit user info) - only a user can edit their own info
	 * a user can only edit certain information (they can not add themselves to a team, or project)
	 * if user does not exist OR deleted throw exception
	 * if user credentials do not match throw exception
	 * set fields
	 */
	@Override
	public UserResponseDTO patchUser(String userName, UserEditRequestDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userName);
		if (findUser.isEmpty() || findUser.get().getIsDeleted()) {
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userName));
		}

		if(!findUser.get().getUserName().equals(userRequest.getCredentials().getUserName()) || !findUser.get().getPassword().equals(userRequest.getCredentials().getPassword())) {
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

	/*
	 * if user not found or deleted throw exception
	 * if boss not found or deleted throw exception
	 * verify boss's credentials
	 * if project exists, set project's user property to this user
	 * else throw exception
	 */
	@Override
	public UserResponseDTO assignProject(String userName, UserRequestAssignProjectDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userName);	
		Optional<User> findBoss = userRepo.findByUserName(userRequest.getCredentials().getUserName());
		if(findUser.isEmpty() || findUser.get().getIsDeleted()){
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userName));
		}
		if(findBoss.isEmpty() || findBoss.get().getIsDeleted()){
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userRequest.getCredentials().getUserName()));
		}
		
		if(!findBoss.get().getUserName().equals(userRequest.getCredentials().getUserName()) || 
				!findUser.get().getPassword().equals(userRequest.getCredentials().getPassword())) {
			throw new BadRequestException("Username/Password do not match.");
		}
		Optional<Project> findProject = projectRepo.findByName(userRequest.getProjectName());
		if(findProject.isPresent()) {
			findProject.get().setUser(findUser.get());
			projectRepo.saveAndFlush(findProject.get());
		}else {
			throw new NotFoundException(String.format("Project with name: '%s' not found.", userRequest.getProjectName()));
		}
		return userMap.EntityToDTO(userRepo.saveAndFlush(findUser.get()));//TODO return project DTO ?
	}

	/*
	 * if user not found or deleted throw exception
	 * if boss not found or deleted throw exception
	 * verify boss's credentials
	 * if company exists, add user to company's list of users
	 * else throw exception
	 */
	@Override
	public UserResponseDTO assignCompany(String userName, UserRequestAssignCompanyDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userName);	
		Optional<User> findBoss = userRepo.findByUserName(userRequest.getCredentials().getUserName());
		if(findUser.isEmpty() || findUser.get().getIsDeleted()){
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userName));
		}
		if(findBoss.isEmpty() || findBoss.get().getIsDeleted()){
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userRequest.getCredentials().getUserName()));
		}
		
		if(!findBoss.get().getUserName().equals(userRequest.getCredentials().getUserName()) || 
				!findUser.get().getPassword().equals(userRequest.getCredentials().getPassword())) {
			throw new BadRequestException("Username/Password do not match.");
		}
		Optional<Company> findCompany = companyRepo.findByCompanyName(userRequest.getCompanyName());
		if(findCompany.isPresent()) {
			findUser.get().setUserCompany(findCompany.get());
			userRepo.saveAndFlush(findUser.get());
		}else {
			throw new NotFoundException(String.format("Company with name: '%s' not found.", userRequest.getCompanyName()));
		}
		return userMap.EntityToDTO(userRepo.saveAndFlush(findUser.get()));//TODO return company DTO?
	}

	/*
	 * delete user (only a user can delete himself)
	 */
	@Override
	public UserResponseDTO deleteUser(String userName, UserSignInRequestDTO userRequest) {
		Optional<User> findUser = userRepo.findByUserName(userName);	
		if(findUser.isEmpty() || findUser.get().getIsDeleted()){
			throw new NotFoundException(String.format("User name: '%s' not found or is deleted.", userName));
		}
		if(!findUser.get().getUserName().equals(userRequest.getUserName()) || !findUser.get().getPassword().equals(userRequest.getPassword())) {
			throw new BadRequestException("Username/Password do not match.");
		}
		findUser.get().setIsDeleted(true);
		userRepo.saveAndFlush(findUser.get());
		return userMap.EntityToDTO(findUser.get());
	}
}
