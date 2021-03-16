package com.cooksys.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.repositories.ProjectRepository;
import com.cooksys.server.repositories.RoleRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
	private UserRepository userRepository;
	private CompanyRepository companyRepository;
	private RoleRepository roleRepository;
	private TeamRepository teamRepository;
	private ProjectRepository projectRepository;
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		Role role = new Role();
		Team team = new Team();
		Project project = new Project();
		Company company = new Company();
		
	}
	

}
