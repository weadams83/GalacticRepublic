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
public class DatabaseSeeder implements CommandLineRunner{
	private CompanyRepository companyRepo;
	private TeamRepository teamRepo;
	private UserRepository userRepo;
	private ProjectRepository projectRepo;
	private RoleRepository roleRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Company dunderMifflin = new Company();
		dunderMifflin.setCompanyName("Dunder Mifflin");
		dunderMifflin.setCompanyDescription("Paper and paper accessories.");
		
		Company krustyKrab = new Company();
		krustyKrab.setCompanyName("Krusty Krab");
		krustyKrab.setCompanyDescription("The squeaking of the horrible boots!");
		
		companyRepo.saveAndFlush(dunderMifflin);
		companyRepo.saveAndFlush(krustyKrab);
		
		Team salesTeam = new Team();
		Team accountingTeam = new Team();
		
		salesTeam.setParentCompany(dunderMifflin);
		salesTeam.setTeamName("Sales");
		salesTeam.setTeamDescription("Sell products to Clients.");

		accountingTeam.setParentCompany(dunderMifflin);
		accountingTeam.setTeamName("Accounting");
		accountingTeam.setTeamDescription("Crunch the numbers.");
		
		Team kitchen = new Team();
		Team cashier = new Team();
		
		kitchen.setParentCompany(krustyKrab);
		kitchen.setTeamName("Kitchen Staff");
		kitchen.setTeamDescription("How the Sausage is made.");
		
		cashier.setParentCompany(krustyKrab);
		cashier.setTeamName("Service Staff");
		cashier.setTeamDescription("Customer Service");
		
		teamRepo.saveAndFlush(salesTeam);
		teamRepo.saveAndFlush(accountingTeam);
		teamRepo.saveAndFlush(kitchen);
		teamRepo.saveAndFlush(cashier);

		
		Role worker = new Role();
		worker.setRoleTitle("Team Member");
		Role comp = new Role();
		comp.setRoleTitle("Manager");
		
		roleRepo.saveAndFlush(worker);
		roleRepo.saveAndFlush(comp);
				
		User Jim = new User();
		Jim.setFirstName("Jim");
		Jim.setLastName("Halpert");
		Jim.setPassword("Pranks");
		Jim.setAssociatedTeam(salesTeam);
		Jim.setUserRole(worker);
		Jim.setUserName("Loki");
		
		User Dwight = new User();
		Dwight.setFirstName("Dwight");
		Dwight.setLastName("Schrute");
		Dwight.setPassword("Beets");
		Dwight.setAssociatedTeam(salesTeam);
		Dwight.setUserRole(worker);
		Dwight.setUserName("Darth Shrewt");
		
		User Stanley = new User();
		Stanley.setFirstName("Stanley");
		Stanley.setLastName("Hudson");
		Stanley.setPassword("Money");
		Stanley.setAssociatedTeam(accountingTeam);
		Stanley.setUserRole(worker);
		Stanley.setUserName("Stanley0689");
		
		User Ryan = new User();
		Ryan.setFirstName("Ryan");
		Ryan.setLastName("Howard");
		Ryan.setPassword("Temp");
		Ryan.setAssociatedTeam(accountingTeam);
		Ryan.setUserRole(worker);
		Ryan.setUserName("Wunderkind");
		
		User spongeBob = new User();
		spongeBob.setFirstName("Spongebob");
		spongeBob.setLastName("Squarepants");
		spongeBob.setPassword("Gary");
		spongeBob.setAssociatedTeam(kitchen);
		spongeBob.setUserRole(worker);
		spongeBob.setUserName("The Quickster");
		
		User squidWard = new User();
		squidWard.setFirstName("Squidward");
		squidWard.setLastName("Tentacles");
		squidWard.setPassword("Clarinet");
		squidWard.setAssociatedTeam(cashier);
		squidWard.setUserRole(worker);
		squidWard.setUserName("Captain Magma");
		
		User mrKrabbs = new User();
		mrKrabbs.setFirstName("Mr.");
		mrKrabbs.setLastName("Krabbs");
		mrKrabbs.setPassword("Money");
		mrKrabbs.setUserRole(comp);
		mrKrabbs.setUserCompany(krustyKrab);
		mrKrabbs.setUserName("JP Lobster");
		
		User michael = new User();
		michael.setFirstName("Michael");
		michael.setLastName("Scott");
		michael.setPassword("Friendship");
		michael.setUserRole(comp);
		michael.setUserCompany(dunderMifflin);
		michael.setUserName("Michael Scarn");
		
		userRepo.saveAndFlush(mrKrabbs);
		userRepo.saveAndFlush(michael);
		userRepo.saveAndFlush(Jim);
		userRepo.saveAndFlush(Dwight);
		userRepo.saveAndFlush(Stanley);
		userRepo.saveAndFlush(Ryan);
		userRepo.saveAndFlush(spongeBob);
		userRepo.saveAndFlush(squidWard);
		
		
		Project manageKrustyKrab = new Project();
		manageKrustyKrab.setUser(mrKrabbs);
		manageKrustyKrab.setName("Count ma money!");
		manageKrustyKrab.setDescription("Stop the squeaking of horrible boots!");

		
		Project managedunderMifflin = new Project();
		managedunderMifflin.setUser(michael);
		managedunderMifflin.setName("Make Friends, Don't die alone.");
		managedunderMifflin.setDescription("Make more 'thats what she said' jokes.");

		
		Project sellPaper = new Project();
		sellPaper.setTeam(salesTeam);
		sellPaper.setName("Sell Paper");
		sellPaper.setDescription("Without watermarks.");
		
		Project crunchNumbers = new Project();
		crunchNumbers.setTeam(accountingTeam);
		crunchNumbers.setName("Count Paper");
		crunchNumbers.setDescription("At least the sales team can get away from Michael.");

		
		Project prankDwight = new Project();
		prankDwight.setUser(Jim);
		prankDwight.setName("Prank Dwight.");
		prankDwight.setDescription("Bears, Beets, BattleStar Galactica.");

		Project flipBurgers = new Project();
		flipBurgers.setTeam(kitchen);
		flipBurgers.setName("The perfect burger.");
		flipBurgers.setDescription("Imagination!");

		
		Project serviceCust = new Project();
		serviceCust.setTeam(cashier);
		serviceCust.setName("Endure.");
		serviceCust.setDescription("Envy SpongeBob.");

		
		Project yolo = new Project();
		yolo.setUser(spongeBob);
		yolo.setName("Living life like GOAT.");
		yolo.setDescription("Bring it around town!");

		projectRepo.saveAndFlush(manageKrustyKrab);
		projectRepo.saveAndFlush(managedunderMifflin);
		projectRepo.saveAndFlush(sellPaper);
		projectRepo.saveAndFlush(crunchNumbers);
		projectRepo.saveAndFlush(prankDwight);
		projectRepo.saveAndFlush(flipBurgers);
		projectRepo.saveAndFlush(serviceCust);
		projectRepo.saveAndFlush(yolo);

	}

}