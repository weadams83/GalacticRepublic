package com.cooksys.server.services.impl;

import java.sql.Timestamp;
import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.ProjectCreateRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.DTOs.UserSignInRequestDTO;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.ImUsedException;
import com.cooksys.server.mappers.ProjectMapper;
import com.cooksys.server.repositories.ProjectRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.ProjectService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;
	private UserRepository userRepo;
	private ProjectMapper projectMapper;

	@Override
	public List<ProjectResponseDTO> getAllProjects() {
		return projectMapper.entitiesToResponseDTOs(projectRepository.findAllByIsDeletedFalse());
	}

	@Override
	public ProjectResponseDTO getProjectByName(String name) {
		Optional<Project> findProject = projectRepository.findByName(name);
		Utils.validateProjectExistsAndNotDeleted(findProject, name);
		return projectMapper.entityToResponseDTO(findProject.get());
	}
	
	//TODO: should only users with role "Company" be able to create projects?
	@Override
	public ProjectResponseDTO createProject(ProjectCreateRequestDTO projectRequestDTO) {
		Optional<Project> findProject = projectRepository.findByName(projectRequestDTO.getProject().getName());
		Optional<User> findUser = userRepo.findByUserName(projectRequestDTO.getCredentials().getUserName());
		if(findProject.isPresent()) {
			throw new ImUsedException(String.format("Project with name: '%s' already taken", projectRequestDTO.getProject().getName()));
		}
		Utils.validateUserExistsAndNotDeleted(findUser, projectRequestDTO.getCredentials().getUserName());
		Utils.validateNewUser(findUser);
		
		Project projectToSave = projectMapper.DTOtoEntity(projectRequestDTO.getProject());
		projectToSave.setUser(findUser.get());
		projectToSave.setUpdated(new Timestamp(System.currentTimeMillis()));
		projectToSave.setUpdatedBy(findUser.get());
		return projectMapper.entityToResponseDTO(projectRepository.saveAndFlush(projectToSave));
	}

	@Override
	public ProjectResponseDTO updateProject(String projectName, ProjectCreateRequestDTO projectRequestDTO) {
		Optional<Project> findProject = projectRepository.findByName(projectName);
		Optional<User> findUser = userRepo.findByUserName(projectRequestDTO.getCredentials().getUserName());
		Utils.validateProjectExistsAndNotDeleted(findProject, projectName);
		Utils.validateUserExistsAndNotDeleted(findUser, projectRequestDTO.getCredentials().getUserName());
		Utils.validateCredentials(findUser, projectRequestDTO.getCredentials().getUserName(), projectRequestDTO.getCredentials().getPassword());
		Utils.validateNewUser(findUser);
		Utils.validateUserIsAssignedProject(findUser,findProject);
		
		findProject.get().setName(projectRequestDTO.getProject().getName());
		findProject.get().setDescription(projectRequestDTO.getProject().getDescription());
		findProject.get().setUpdated(new Timestamp(System.currentTimeMillis()));
		findProject.get().setUpdatedBy(findUser.get());
		
		return projectMapper.entityToResponseDTO(projectRepository.saveAndFlush(findProject.get()));
	}

	@Override
	public ProjectResponseDTO deleteProject(String projectName, UserSignInRequestDTO credentials) {
		Optional<Project> findProject = projectRepository.findByName(projectName);
		Optional<User> findUser = userRepo.findByUserName(credentials.getUserName());
		Utils.validateProjectExistsAndNotDeleted(findProject, projectName);
		Utils.validateUserExistsAndNotDeleted(findUser, credentials.getUserName());
		Utils.validateCredentials(findUser, credentials.getUserName(), credentials.getPassword());
		Utils.validateNewUser(findUser);
		Utils.validateUserIsAssignedProject(findUser,findProject);

		findProject.get().setIsDeleted(true);
		findProject.get().setUpdated(new Timestamp(System.currentTimeMillis()));
		findProject.get().setUpdatedBy(findUser.get());
		
		projectRepository.saveAndFlush(findProject.get());
		return projectMapper.entityToResponseDTO(findProject.get());
	}
}