package com.cooksys.server.mappers;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.server.DTOs.CompanyCreateRequestDTO;
import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserDTO;
import com.cooksys.server.DTOs.UserEditRequestDTO;
import com.cooksys.server.DTOs.UserRequestAssignProjectDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.DTOs.UserSignInRequestDTO;
import com.cooksys.server.entities.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
	User DTOtoEntity(UserDTO userDTO);
	
	UserResponseDTO EntityToDTO(User user);
	
	List<UserResponseDTO> EntitiesToDTO(List<User> users);

	User UserSignInDTOtoEntity(UserSignInRequestDTO userSignInRequestDTO);
}
