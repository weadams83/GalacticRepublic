package com.cooksys.server.mappers;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cooksys.server.DTOs.UserSignInRequestDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserResponseDTO EntityToDTO(User user);

	User DTOtoEntity(UserSignInRequestDTO userSignInRequestDTO);
}
