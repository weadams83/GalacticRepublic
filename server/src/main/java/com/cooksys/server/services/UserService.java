package com.cooksys.server.services;

import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserEditRequestDTO;
import com.cooksys.server.DTOs.UserResponseDTO;

public interface UserService {

	UserResponseDTO getUser(String userName);

	UserResponseDTO postUser(UserCreateRequestDTO userRequest);

	UserResponseDTO patchUser(String userName, UserEditRequestDTO userRequest);

}
