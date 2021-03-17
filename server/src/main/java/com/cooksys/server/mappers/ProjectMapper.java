package com.cooksys.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.server.DTOs.ProjectRequestDTO;
import com.cooksys.server.DTOs.ProjectResponseDTO;
import com.cooksys.server.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

	Project requestDtoToEntity(ProjectRequestDTO projectRequestDTO);

	ProjectResponseDTO entityToResponseDTO(Project project);

	List<ProjectResponseDTO> entitiesToResponseDTOs(List<Project> projects);

}