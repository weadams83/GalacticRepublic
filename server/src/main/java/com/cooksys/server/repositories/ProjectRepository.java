package com.cooksys.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Team;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findAll();

	Optional<Project> findByName(String name);

	Optional<Project> findById(Long id);

	Optional<Project> findByTeam(Team team);
}