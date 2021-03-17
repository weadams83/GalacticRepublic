package com.cooksys.server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Team;
import com.cooksys.server.services.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("project")
@AllArgsConstructor
public class ProjectController {

	private ProjectService projectService;

	@GetMapping
	public List<ProjectResponseDTO> getAllProjects() {
		return projectService.getAllProjects();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO projectRequestDTO) {
		return projectService.createProject(projectRequestDTO);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectResponseDTO getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id);
	}

	@GetMapping("/{projectName}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectResponseDTO getProjectByName(@PathVariable String projectName) {
		return projectService.getProjectByName(projectName);
	}

	@GetMapping("/{projectTeam}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectResponseDTO getProjectByTeam(@PathVariable Team team) {
		return projectService.getProjectByTeam(team);
	}

	@PutMapping("/{updateProjectName}")
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectResponseDTO updateProjectName(@PathVariable Long id,
			@RequestBody ProjectRequestDTO projectRequestDTO) {
		return projectService.updateProjectName(id, projectRequestDTO);
	}

	@PutMapping("/{updateProjectDescription}")
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectResponseDTO updateProjectDescription(@PathVariable Long id,
			@RequestBody ProjectRequestDTO projectRequestDTO) {
		return projectService.updateProjectDescription(id, projectRequestDTO);
	}

	@PutMapping("/{upadateProjectUsers}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectResponseDTO updateProjectUsers(@PathVariable Long id,
			@RequestBody ProjectRequestDTO projectRequestDTO) {
		return projectService.updateProjectUsers(id, projectRequestDTO);
	}

	@PutMapping("/{upadateProjectTeam}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectResponseDTO updateProjectTeam(@PathVariable Long id,
			@RequestBody ProjectRequestDTO projectRequestDTO) {
		return projectService.updateProjectTeam(id, projectRequestDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectResponseDTO deleteProject(@PathVariable Long id) {
		return projectService.deleteProject(id);
	}

}