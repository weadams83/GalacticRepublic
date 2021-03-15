package com.cooksys.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.server.entities.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
