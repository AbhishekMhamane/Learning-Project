package com.example.employeeservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.employeeservice.model.Organization;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

}
