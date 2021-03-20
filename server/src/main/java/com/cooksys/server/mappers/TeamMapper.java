package com.cooksys.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.server.DTOs.TeamRequestDTO;
import com.cooksys.server.DTOs.TeamResponseDTO;
import com.cooksys.server.DTOs.UserResponseTeamDTO;
import com.cooksys.server.entities.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {
	TeamResponseDTO EntityToDto(Team team);

	Team DTOtoEntity(TeamRequestDTO teamRequestDTO);

	List<TeamResponseDTO> entitesToResponseDTO(List<Team> getAllTeams);
}
