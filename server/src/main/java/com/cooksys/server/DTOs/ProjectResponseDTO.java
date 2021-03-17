package com.cooksys.server.DTOs;

import java.sql.Timestamp;
import java.util.List;

import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectResponseDTO {

	private Long id;

	private String projectName;

	private String description;

	private Timestamp created;

	private Timestamp updated;

	private List<User> projectUsers;

	private Team projectTeam;

	private boolean isDeleted;

}
