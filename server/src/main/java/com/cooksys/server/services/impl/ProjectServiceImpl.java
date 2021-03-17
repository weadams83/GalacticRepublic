package com.cooksys.server.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Project;
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
	public ResponseEntity<ProjectResponseDTO> getProjectById(Long id) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		if (optionalProject.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(projectMapper.entityToResponseDTO(optionalProject.get()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProjectResponseDTO> getProjectById(String projectName) {
		Optional<Project> optionalProject = projectRepository.findByprojectName(projectName);
		if (optionalProject.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(projectMapper.entityToResponseDTO(optionalProject.get()), HttpStatus.OK);
	}

}
