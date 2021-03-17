package com.cooksys.server.services;

import com.cooksys.server.DTOs.UserResponseDTO;

public interface UserService {

	UserResponseDTO getUser(String userName);

}
