package com.cooksys.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.services.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("project")
@AllArgsConstructor
public class ProjectController {

	private ProjectService projectService;

	@GetMapping
	public List<ProjectResponseDTO> getProjects() {
		return projectService.getProjects();
	}

	@PostMapping
	public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO projectRequestDTO) {
		return projectService.createProject(projectRequestDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id);
	}
	
	@GetMapping("/{projectName}")
	public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable String projectName) {
		return projectService.getProjectById(projectName);
	}

}
