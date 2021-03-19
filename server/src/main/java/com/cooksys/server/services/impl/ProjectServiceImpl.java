package com.cooksys.server.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Team;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.mappers.ProjectMapper;
import com.cooksys.server.repositories.ProjectRepository;
import com.cooksys.server.services.ProjectService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;
	private ProjectMapper projectMapper;

	// Helper method to check that project with the entered Id exists
	public Project getProject(Long id) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("Project not found");
		}
		return optionalProject.get();
	}

	@Override
	public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
		Project projectToSave = projectMapper.requestDtoToEntity(projectRequestDTO);
		return projectMapper.EntityToProjectResponseDTO(projectRepository.saveAndFlush(projectToSave));
	}

	@Override
	public List<ProjectResponseDTO> getAllProjects() {
		return projectMapper.entitiesToResponseDTOs(projectRepository.findAll());
	}

	@Override
	public ProjectResponseDTO getProjectById(Long id) {
		return projectMapper.EntityToProjectResponseDTO(getProject(id));
	}

	@Override
	public ProjectResponseDTO getProjectByName(String projectName) {
		Optional<Project> optionalProject = projectRepository.findByName(projectName);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("No project with name: " + projectName + " found.");
		}
		return projectMapper.EntityToProjectResponseDTO(optionalProject.get());
	}

	public ProjectResponseDTO getProjectByTeam(Team team) {
		Optional<Project> optionalProject = projectRepository.findByTeam(team);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("No project with name: " + team + " found.");
		}
		return projectMapper.EntityToProjectResponseDTO(optionalProject.get());
	}

	@Override
	public ProjectResponseDTO updateProjectName(Long id, ProjectRequestDTO projectRequestDTO) {
		Project projectToUpdate = getProject(id);
		projectToUpdate.setName(projectRequestDTO.getName());
		return projectMapper.EntityToProjectResponseDTO(projectRepository.saveAndFlush(projectToUpdate));
	}

	@Override
	public ProjectResponseDTO updateProjectDescription(Long id, ProjectRequestDTO projectRequestDTO) {
		Project projectToUpdate = getProject(id);
		projectToUpdate.setDescription(projectRequestDTO.getDescription());
		return projectMapper.EntityToProjectResponseDTO(projectRepository.saveAndFlush(projectToUpdate));
	}

	@Override
	public ProjectResponseDTO updateProjectUsers(Long id, ProjectRequestDTO projectRequestDTO) {
		Project projectToUpdate = getProject(id);
		projectToUpdate.setUser(projectRequestDTO.getUser());
		return projectMapper.EntityToProjectResponseDTO(projectRepository.saveAndFlush(projectToUpdate));
	}

	@Override
	public ProjectResponseDTO updateProjectTeam(Long id, ProjectRequestDTO projectRequestDTO) {
		Project projectToUpdate = getProject(id);
		projectToUpdate.setTeam(projectRequestDTO.getProjectTeam());
		return projectMapper.EntityToProjectResponseDTO(projectRepository.saveAndFlush(projectToUpdate));
	}

	@Override
	public ProjectResponseDTO deleteProject(Long id) {
		Project projectToDelete = getProject(id);
		projectToDelete.setIsDeleted(true);
		return projectMapper.EntityToProjectResponseDTO(projectRepository.saveAndFlush(projectToDelete));
	}

}