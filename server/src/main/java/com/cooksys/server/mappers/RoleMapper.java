package com.cooksys.server.mappers;

import org.mapstruct.Mapper;

<<<<<<< HEAD
=======
import com.cooksys.server.DTOs.RoleDTO;
import com.cooksys.server.entities.Role;

>>>>>>> 26e6fed64b33a57c30d563e33512cd8b2a6a0aea
@Mapper(componentModel = "spring")
public interface RoleMapper {
	Role DTOtoEntity(RoleDTO roleDTO);

}
