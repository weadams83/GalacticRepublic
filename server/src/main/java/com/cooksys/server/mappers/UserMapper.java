package com.cooksys.server.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserEditRequestDTO;
import com.cooksys.server.DTOs.UserRequestAssignProjectDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.DTOs.UserSignInRequestDTO;
import com.cooksys.server.entities.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

	UserResponseDTO EntityToDTO(User user);

	User DTOtoEntity(UserSignInRequestDTO userSignInRequestDTO);
	
	@Mappings({
		@Mapping(target="userRole.roleTitle", source="userCreateRequestDTO.roleTitle")
		})
	User CreateDTOtoEntity(UserCreateRequestDTO userCreateRequestDTO);
	
	User EditDTOtoEntity(UserEditRequestDTO userEditRequestDTO);
	
	User UserSignInDTOtoEntity(UserSignInRequestDTO userSignInRequestDTO);
}
