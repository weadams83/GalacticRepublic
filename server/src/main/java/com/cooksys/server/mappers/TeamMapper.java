package com.cooksys.server.mappers;

import org.mapstruct.Mapper;

import com.cooksys.server.DTOs.TeamDTO;
import com.cooksys.server.entities.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {
	Team DTOtoEntity (TeamDTO teamDTO);
	TeamDTO entityToEntity (Team team);
}
