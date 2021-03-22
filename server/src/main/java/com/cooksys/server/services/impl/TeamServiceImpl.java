package com.cooksys.server.services.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.cooksys.server.DTOs.TeamRequestDTO;
import com.cooksys.server.DTOs.TeamResponseDTO;
import com.cooksys.server.DTOs.UserSignInRequestDTO;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.ImUsedException;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.TeamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
	private TeamRepository teamRepository;
	private UserRepository userRepo;
	private TeamMapper teamMapper;

	
	
	@Override
	public TeamResponseDTO getTeam(String teamName) {
		Optional<Team> teamToGet = teamRepository.findByTeamNameAndIsDeletedFalse(teamName);
		Utils.validateTeamExistsAndNotDeleted(teamToGet,teamName);
		return teamMapper.EntityToDto(teamToGet.get());
	}

	@Override
	public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) {
		Optional<Team> findTeam = teamRepository.findByTeamName(teamRequestDTO.getTeam().getTeamName());
		if(findTeam.isPresent()) {
			if(findTeam.get().getIsDeleted()) {
				findTeam.get().setIsDeleted(false);
				return teamMapper.EntityToDto(teamRepository.saveAndFlush(findTeam.get()));
			}else {
				throw new ImUsedException(String.format("Team with name: '%s' is already taken.", teamRequestDTO.getTeam().getTeamName()));
			}
		}
		Optional<User> findUser = userRepo.findByUserName(teamRequestDTO.getCredentials().getUserName());
		Utils.validateUserExistsAndNotDeleted(findUser, teamRequestDTO.getCredentials().getUserName());
		Utils.validateNewUser(findUser);
		Utils.validateCredentials(findUser,teamRequestDTO.getCredentials().getUserName(),teamRequestDTO.getCredentials().getPassword());
		Utils.validateAuthorization(findUser, teamRequestDTO.getCredentials().getUserName());

		Team teamToSave = teamMapper.DTOtoEntity(teamRequestDTO.getTeam());
		teamToSave.setParentCompany(findUser.get().getUserCompany());
		teamToSave = teamRepository.saveAndFlush(teamToSave);
		findUser.get().setAssociatedTeam(teamToSave);
		findUser.get().setUpdated(new Timestamp(System.currentTimeMillis()));
		findUser.get().setUpdatedBy(findUser.get());
		userRepo.saveAndFlush(findUser.get());
		return teamMapper.EntityToDto(teamToSave);
	}

	@Override
	public List<TeamResponseDTO> getAllTeams() {
		return teamMapper.entitesToResponseDTO(teamRepository.findAllByIsDeletedFalse());
	}

	@Override
	public TeamResponseDTO updateTeam(String teamName, TeamRequestDTO teamRequestDTO) {
		Optional<Team> optionalTeam = teamRepository.findByTeamNameIgnoreCase(teamName);
		Optional<User> findUser = userRepo.findByUserName(teamRequestDTO.getCredentials().getUserName());
		Utils.validateTeamExistsAndNotDeleted(optionalTeam,teamName);
		Utils.validateUserExistsAndNotDeleted(findUser, teamRequestDTO.getCredentials().getUserName());
		Utils.validateNewUser(findUser);
		Utils.validateAuthorization(findUser, teamRequestDTO.getCredentials().getUserName());
		Utils.validateCredentials(findUser, teamRequestDTO.getCredentials().getUserName(), teamRequestDTO.getCredentials().getPassword());
		Utils.validateBossIsSameCompanyAsTeam(optionalTeam, findUser);
		
		Team teamToUpdate = optionalTeam.get();
		teamToUpdate.setTeamDescription(teamRequestDTO.getTeam().getTeamDescription());
		teamToUpdate.setTeamName(teamRequestDTO.getTeam().getTeamName());
		return teamMapper.EntityToDto(teamRepository.saveAndFlush(teamToUpdate));
	}

	@Override
	public TeamResponseDTO deleteTeam(String teamName, UserSignInRequestDTO teamRequestDTO) {
		Optional<Team> optionalTeam = teamRepository.findByTeamNameIgnoreCase(teamName);
		Optional<User> findUser = userRepo.findByUserName(teamRequestDTO.getUserName());
		Utils.validateTeamExistsAndNotDeleted(optionalTeam,teamName);
		Utils.validateUserExistsAndNotDeleted(findUser, teamRequestDTO.getUserName());
		Utils.validateNewUser(findUser);
		Utils.validateAuthorization(findUser, teamRequestDTO.getUserName());
		Utils.validateCredentials(findUser, teamRequestDTO.getUserName(), teamRequestDTO.getPassword());
		Utils.validateBossIsSameCompanyAsTeam(optionalTeam, findUser);
		
		Team teamToUpdate = optionalTeam.get();
		teamToUpdate.setIsDeleted(true);
		return teamMapper.EntityToDto(teamRepository.saveAndFlush(teamToUpdate));
	}
}
