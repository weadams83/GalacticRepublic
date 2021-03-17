package com.cooksys.server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.server.DTOs.TeamRequestDTO;
import com.cooksys.server.DTOs.TeamResponseDTO;
import com.cooksys.server.services.TeamService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("team")
@AllArgsConstructor
public class TeamController {
	private TeamService teamService;

	@GetMapping
	public List<TeamResponseDTO> getAllTeams(TeamRequestDTO teamRequestDTO) {
		return teamService.getAllTeams();
	}

	@GetMapping("/{teamName}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TeamResponseDTO> getTeam(@PathVariable("teamName") String teamName) {
		return teamService.getTeam(teamName);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public TeamResponseDTO createTeam(@RequestBody TeamRequestDTO teamRequestDTO) {
		return teamService.createTeam(teamRequestDTO);
	}

	@PatchMapping("/update/{teamName}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable String teamName,
			@RequestBody TeamRequestDTO teamRequestDTO) {
		return teamService.updateTeam(teamName, teamRequestDTO);
	}
}
