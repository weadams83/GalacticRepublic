package com.cooksys.server.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;

public interface ProjectService {

	List<ProjectResponseDTO> getProjects();

	ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);

	ResponseEntity<ProjectResponseDTO> getProjectById(Long id);

	ResponseEntity<ProjectResponseDTO> getProjectById(String projectName);

}
