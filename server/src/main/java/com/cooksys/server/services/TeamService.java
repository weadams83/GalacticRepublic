package com.cooksys.server.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cooksys.server.DTOs.TeamRequestDTO;
import com.cooksys.server.DTOs.TeamResponseDTO;

public interface TeamService {

	ResponseEntity<TeamResponseDTO> getTeam(String teamName);

	TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO);

	List<TeamResponseDTO> getAllTeams();

	ResponseEntity<TeamResponseDTO> updateTeam(String teamName, TeamRequestDTO teamRequestDTO);

}
