package com.cooksys.server.services;

import java.util.List;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Team;

public interface ProjectService {

	List<ProjectResponseDTO> getProjects();

	ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO getProjectById(Long id);

	ProjectResponseDTO getProjectByName(String projectName);

	ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO getProjectByTeam(Team team);

}
