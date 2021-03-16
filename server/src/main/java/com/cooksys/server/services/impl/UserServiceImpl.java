package com.cooksys.server.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private UserRepository userRepo;
	private UserMapper userMap;

	@Override
	public UserResponseDTO getUser(String userName) {
		Optional<User> findUser = userRepo.findByUserName(userName);
		if(findUser.isEmpty()) {
			throw new NotFoundException(String.format("User with user name: '%s' could not be found.", userName));
		}
		return userMap.EntityToDTO(findUser.get());
	}

}
