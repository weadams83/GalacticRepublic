package com.cooksys.server.mappers;

import org.mapstruct.Mapper;

import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
	ProjectResponseDTO EntityToProjectResponseDTO(Project project);

}
