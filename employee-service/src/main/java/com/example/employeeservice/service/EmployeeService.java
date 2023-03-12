package com.example.employeeservice.service;

import com.example.employeeservice.custom_exceptions.HandleCustomException;
import com.example.employeeservice.model.Department;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.repository.DepartmentRepository;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.repository.OrganizationRepository;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeRepository empRepo;

  @Autowired
  private OrganizationRepository orgRepo;

  @Autowired
  private DepartmentRepository deptRepo;

  @Autowired
  EmployeeService(EmployeeRepository empRepo) {
    this.empRepo = empRepo;
  }

  public Organization getOrganizationById(Integer orgId) {
    Optional<Organization> opOrg = orgRepo.findById(orgId);
    return opOrg.get();
  }

  public Department getDepartmentById(Integer deptId) {
    Optional<Department> opDept = deptRepo.findById(deptId);
    return opDept.get();
  }

  public List<Employee> getAllEmployee() {
    try {
      return empRepo.findAll();
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public Employee getEmployeeById(Integer empId) {
    try {
      boolean isEmpPresent = empRepo.existsById(empId);

      if (isEmpPresent) {
        Optional<Employee> empOptional = empRepo.findById(empId);

        Employee emp = empOptional.get();

        return emp;
      } else {
        throw new HandleCustomException(
          "400",
          "Employee not present with given id"
        );
      }
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public List<Employee> getAllEmployeesByOrg(Integer orgId) {
    try {
      Organization org = getOrganizationById(orgId);

      if (org != null) {
        List<Employee> listEmp = empRepo.findByOrg(org);
        return listEmp;
      } else {
        throw new HandleCustomException(
          "400",
          "Organization is not present by given id"
        );
      }
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public List<Employee> getAllEmployeesByDept(Integer deptId) {
    try {
      Department dept = getDepartmentById(deptId);

      if (dept != null) {
        List<Employee> listEmp = empRepo.findByDept(dept);
        return listEmp;
      } else {
        throw new HandleCustomException(
          "400",
          "Department is not present by given id"
        );
      }
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public Employee addEmployee(Integer orgId, Integer deptId, Employee emp) {
    try {
      Organization org = getOrganizationById(orgId);
      Department dept = getDepartmentById(deptId);

      if (org != null) {
        if (dept != null) {
          if (!emp.getFirstName().isBlank() && !emp.getFirstName().isEmpty()) {
            if (!emp.getLastName().isBlank() && !emp.getLastName().isEmpty()) {
              if (!emp.getEmail().isBlank() && !emp.getEmail().isEmpty()) {
                if (
                  !emp.getMobileNo1().isBlank() && !emp.getMobileNo1().isEmpty()
                ) {
                  emp.setOrg(org);
                  emp.setDept(dept);
                  return empRepo.save(emp);
                } else {
                  throw new HandleCustomException("400", "Enter valid mobile");
                }
              } else {
                throw new HandleCustomException("400", "Enter valid email");
              }
            } else {
              throw new HandleCustomException("400", "Enter valid last name");
            }
          } else {
            throw new HandleCustomException("400", "Enter valid first name");
          }
        } else {
          throw new HandleCustomException(
            "400",
            "Department not present with given id"
          );
        }
      } else {
        throw new HandleCustomException(
          "400",
          "Organization not present with given id"
        );
      }
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public void deletetEmployeeById(Integer empId) {
    try {
      boolean isEmpPresent = empRepo.existsById(empId);

      if (isEmpPresent) {
        empRepo.deleteById(empId);
      } else {
        throw new HandleCustomException(
          "400",
          "Employee not present with given id"
        );
      }
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public Employee updateEmployeeById(Integer empId, Employee emp) {
    try {
      Optional<Employee> empOptional = empRepo.findById(empId);
  
      if (empOptional.isPresent()) {
        Employee empRecord = empOptional.get();

        if (
          emp.getFirstName() != null &&
          emp.getFirstName() != empRecord.getFirstName()
        ) {
          empRecord.setFirstName(emp.getFirstName());
        }
        if (
          emp.getLastName() != null &&
          emp.getLastName() != empRecord.getLastName()
        ) {
          empRecord.setLastName(emp.getLastName());
        }
        if (emp.getEmail() != null && emp.getEmail() != empRecord.getEmail()) {
          empRecord.setEmail(emp.getEmail());
        }
        if (
          emp.getMobileNo1() != null &&
          emp.getMobileNo1() != empRecord.getMobileNo1()
        ) {
          empRecord.setMobileNo1(emp.getMobileNo1());
        }
        if (
          emp.getMobileNo2() != null &&
          emp.getMobileNo2() != empRecord.getMobileNo2()
        ) {
          empRecord.setMobileNo2(emp.getMobileNo2());
        }

        return empRepo.save(empRecord);
      } else {
        throw new HandleCustomException(
          "400",
          "Employee not present with given id"
        );
      }
    } catch (HandleCustomException e) {
      throw new HandleCustomException(e.getErrorCode(), e.getErrorMessage());
    } catch (Exception e) {
      throw new HandleCustomException("400", e.getMessage());
    }
  }

  public Employee updateEmployeeDepartment(Integer empId,Integer deptId)
  {
    try
    {
      Optional<Employee> opEmp = empRepo.findById(empId);
      Optional<Department> opDept = deptRepo.findById(deptId);

      if(opEmp.isPresent() && opDept.isPresent())
      {
          Employee emp = opEmp.get();
          emp.setDept(opDept.get());
          
          return empRepo.save(emp);
      }
      else
      {
        throw new HandleCustomException("400","Wrong paramameters !");
      }
    }
    catch(HandleCustomException e)
    {
      throw new HandleCustomException("400", e.getMessage());
    }
    catch(Exception e)
    {
      throw new HandleCustomException("500", e.getMessage());
    }
  }
}
