package com.example.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.organizationservice.model.OrgOfficeLocation;

@Repository
public interface OrgOfficeLocationRepository extends JpaRepository<OrgOfficeLocation,Integer> {

	Optional<OrgOfficeLocation> findByPincode(String pincode);
}
