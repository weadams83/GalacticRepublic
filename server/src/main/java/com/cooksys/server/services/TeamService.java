package com.cooksys.server.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cooksys.server.DTOs.TeamDeleteRequestDTO;
import com.cooksys.server.DTOs.TeamEditRequestDTO;
import com.cooksys.server.DTOs.TeamRequestDTO;
import com.cooksys.server.DTOs.TeamResponseDTO;
import com.cooksys.server.DTOs.UserSignInRequestDTO;

public interface TeamService {

	TeamResponseDTO getTeam(String teamName);

	TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO);

	List<TeamResponseDTO> getAllTeams();

	TeamResponseDTO updateTeam(String teamName, TeamEditRequestDTO teamRequestDTO);

	TeamResponseDTO deleteTeam(String teamName, UserSignInRequestDTO teamRequestDTO);
}
