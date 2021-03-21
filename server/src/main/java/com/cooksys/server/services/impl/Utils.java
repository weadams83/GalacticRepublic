package com.cooksys.server.services.impl;

import java.util.Optional;

import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.NotFoundException;

public class Utils {
	static void validateBossIsSameCompanyAsUser(Optional<User> findUser, Optional<User> findBoss) {
		if(!findUser.get().getUserCompany().equals(findBoss.get().getUserCompany())){
			throw new BadRequestException(String.format("Boss with user name: '%s can not assign user with user name: '%s' "
					+ "as they work for different companies." , findUser.get().getUserName(),findBoss.get().getUserName()));
		}
	}
	
	static void validateNewUser(Optional<User> findUser) {
		if(findUser.get().isNewUser()) {
			throw new BadRequestException(String.format("User with user name: '%s' has not been assigned a Role.", findUser.get().getUserName()));
		}
	}
	
	static void validateAuthorization(Optional<User> findUser,String userName) {
		if(!findUser.get().getUserRole().getRoleTitle().equals("Company")) {
			throw new BadRequestException(String.format("User with user name: '%s' does not have authorization.", userName));
		}
	}

	static void validateCredentials(Optional<User> findUser, String userName, String password) {
		if(!findUser.get().getUserName().equals(userName) || !findUser.get().getPassword().equals(password)) {
			throw new BadRequestException("Username/Password do not match.");
		}
	}
	
	static void validateUserExistsAndNotDeleted(Optional<User> findUser, String userName) {
		if(findUser.isEmpty() || findUser.get().getIsDeleted()) {
			throw new NotFoundException(String.format("User with user name: '%s' could not be found or has been deleted.", userName));

		}
	}

	public static void validateBossWorksForCompany(Optional<User> findUser, Optional<Company> findCompany) {
		if(!findUser.get().getUserCompany().equals(findCompany.get())){
			throw new BadRequestException(String.format("Boss with user name: '%s can not edit company: '%s' "
					+ "as they do not work at that company." , findUser.get().getUserName(),findCompany.get().getCompanyName()));
		}
	}
}
