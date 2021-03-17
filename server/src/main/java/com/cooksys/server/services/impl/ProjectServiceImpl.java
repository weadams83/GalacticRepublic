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

	@Override
	public List<ProjectResponseDTO> getProjects() {
		return projectMapper.entitiesToResponseDTOs(projectRepository.findAll());
	}

	@Override
	public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
		Project projectToSave = projectMapper.requestDtoToEntity(projectRequestDTO);
		return projectMapper.entityToResponseDTO(projectRepository.saveAndFlush(projectToSave));
	}

	@Override
	public ProjectResponseDTO getProjectById(Long id) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("No project with id: " + id + " found.");
		}
		return projectMapper.entityToResponseDTO(optionalProject.get());
	}

	@Override
	public ProjectResponseDTO getProjectByName(String projectName) {
		Optional<Project> optionalProject = projectRepository.findByName(projectName);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("No project with name: " + projectName + " found.");
		}
		return projectMapper.entityToResponseDTO(optionalProject.get());
	}

	public ProjectResponseDTO getProjectByTeam(Team team) {
		Optional<Project> optionalProject = projectRepository.findByTeam(team);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("No project with name: " + team + " found.");
		}
		return projectMapper.entityToResponseDTO(optionalProject.get());
	}

	@Override
	public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		if (optionalProject.isEmpty()) {
			throw new BadRequestException("No project with id: " + id + " found.");
		}
		Project projectToUpdate = optionalProject.get();
		projectToUpdate.setDescription(projectRequestDTO.getDescription());
		projectToUpdate.setProjectName(projectRequestDTO.getProjectName());
		projectToUpdate.setProjectTeam(projectRequestDTO.getTeam());
		projectToUpdate.setProjectUsers(projectRequestDTO.getProjectUsers());
		return projectMapper.entityToResponseDTO(projectRepository.saveAndFlush(projectToUpdate));
	}

}
