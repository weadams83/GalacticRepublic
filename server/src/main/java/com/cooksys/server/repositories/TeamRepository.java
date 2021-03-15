package com.cooksys.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
