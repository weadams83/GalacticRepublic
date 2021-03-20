package com.cooksys.server.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cooksys.server.DTOs.TeamRequestDTO;
import com.cooksys.server.DTOs.TeamResponseDTO;
import com.cooksys.server.entities.Team;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.services.TeamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
	private TeamRepository teamRepository;
	private TeamMapper teamMapper;

	@Override
	public ResponseEntity<TeamResponseDTO> getTeam(String teamName) {
		Optional<Team> teamToGet = teamRepository.findByTeamNameIgnoreCase(teamName);
		if (teamToGet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(teamMapper.EntityToDto(teamToGet.get()), HttpStatus.OK);

	}

	@Override
	public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) {
		Team teamToSave = teamMapper.DTOtoEntity(teamRequestDTO);
		return teamMapper.EntityToDto(teamRepository.saveAndFlush(teamToSave));
	}

	@Override
	public List<TeamResponseDTO> getAllTeams() {

		return teamMapper.entitesToResponseDTO(teamRepository.findAllByIsDeletedFalse());
	}

	@Override
	public ResponseEntity<TeamResponseDTO> updateTeam(String teamName, TeamRequestDTO teamRequestDTO) {
		Optional<Team> optionalTeam = teamRepository.findByTeamNameIgnoreCase(teamName);
		if (optionalTeam.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		Team teamToUpdate = optionalTeam.get();
		teamToUpdate.setTeamDescription(teamRequestDTO.getTeamDescription());
		teamToUpdate.setTeamName(teamRequestDTO.getTeamName());
		return new ResponseEntity<>(teamMapper.EntityToDto(teamRepository.saveAndFlush(teamToUpdate)), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<TeamResponseDTO> deleteTeam(String teamName) {
		Optional<Team> optionalTeam = teamRepository.findByTeamNameIgnoreCase(teamName);
		if (optionalTeam.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		Team teamToUpdate = optionalTeam.get();
		teamToUpdate.setIsDeleted(true);
		return new ResponseEntity<>(teamMapper.EntityToDto(teamRepository.saveAndFlush(teamToUpdate)), HttpStatus.GONE);
	}
}
