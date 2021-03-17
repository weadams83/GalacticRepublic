package com.cooksys.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.DTOs.CompanyResponseDTO;
import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.User;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findBycompanyName(String companyName);



}
