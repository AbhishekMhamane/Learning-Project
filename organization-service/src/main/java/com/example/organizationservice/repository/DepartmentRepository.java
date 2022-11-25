package com.example.organizationservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.organizationservice.model.Department;
import com.example.organizationservice.model.Organization;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

//	@Query(nativeQuery=true, value="select * from department where dept_name = :dname")
//	Department findByDepartmentNameByOrgId(@Param("dname") String dname);
	
	@Query(nativeQuery=true, value="select * from department where org_id = :oid and dept_name = :dname")
	Optional<Department> findByDepartmentByOrgIdAndDeptName(@Param("oid") Integer oid,@Param("dname") String dname);
}
