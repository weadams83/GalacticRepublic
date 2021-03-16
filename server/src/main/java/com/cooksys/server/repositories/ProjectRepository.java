package com.cooksys.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.entities.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {

}
