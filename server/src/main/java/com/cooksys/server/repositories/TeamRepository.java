package com.cooksys.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	Optional<Team> findByTeamName(String teamName);

}
