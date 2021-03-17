package com.cooksys.server.services;

import java.util.List;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Team;

public interface ProjectService {

	ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);

	List<ProjectResponseDTO> getAllProjects();

	ProjectResponseDTO getProjectById(Long id);

	ProjectResponseDTO getProjectByName(String name);

	ProjectResponseDTO updateProjectName(Long id, ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO getProjectByTeam(Team team);

	ProjectResponseDTO updateProjectUsers(Long id, ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO updateProjectDescription(Long id, ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO updateProjectTeam(Long id, ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO deleteProject(Long id);
}