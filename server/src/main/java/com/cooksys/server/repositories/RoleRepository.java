package com.cooksys.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
