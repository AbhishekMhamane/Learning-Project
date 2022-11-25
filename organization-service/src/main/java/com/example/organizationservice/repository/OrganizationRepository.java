package com.example.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.organizationservice.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

	Optional<Organization> findByOrgName(String orgName);
	Optional<Organization> findByOrgEmail(String orgEmail);
	Optional<Organization> findByOrgMobileNo(String orgMobileNo);
}
