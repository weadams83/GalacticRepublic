package com.cooksys.server.DTOs;

import com.cooksys.server.entities.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEditRequestDTO {
	
	private UserSignInRequestDTO credentials; 
	
	private UserDTO newData;
}
