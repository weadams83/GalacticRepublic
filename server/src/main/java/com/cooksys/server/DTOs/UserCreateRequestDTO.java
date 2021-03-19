package com.cooksys.server.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateRequestDTO {
	private String userName;

	private String firstName;

	private String lastName;
	
	private String password;
	
	private String companyName;
}
