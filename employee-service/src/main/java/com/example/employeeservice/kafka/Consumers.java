package com.example.employeeservice.kafka;

import com.example.employeeservice.model.Department;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.repository.DepartmentRepository;
import com.example.employeeservice.repository.OrganizationRepository;
import com.example.organizationservice.event_dtos.DepartmentEvent;
import com.example.organizationservice.event_dtos.OrganizationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Consumers {

  static final Logger log = LoggerFactory.getLogger(Consumers.class);

  @Autowired
  private OrganizationRepository orgRepo;

  @Autowired
  private DepartmentRepository deptRepo;

  @KafkaListener(
    topics = "${org.topic.name}",
    containerFactory = "organizationEventKafkaConsumerFactory"
  )
  public void consumeOrganizationEvent(OrganizationEvent orgEvent) {
    log.info("Consumed new organization event from employee service");
    log.info(orgEvent.toString());

    Organization org = new Organization();

    org.setOrgId(orgEvent.getOrganization().getOrgId());
    org.setOrgName(orgEvent.getOrganization().getOrgName());
    org.setOrgOwner(orgEvent.getOrganization().getOrgOwner());
    org.setOrgEmail(orgEvent.getOrganization().getOrgEmail());
    org.setOrgMobileNo(orgEvent.getOrganization().getOrgMobileNo());

    if (!orgEvent.isDelete()) {
      orgRepo.save(org);
      log.info("Saved organization in db " + org.toString());
    } else {
      if (orgRepo.existsById(org.getOrgId())) {
        orgRepo.deleteById(org.getOrgId());
        log.info("Deleted organization in db " + org.toString());
      }
    }
  }

  @KafkaListener(
    topics = "${dept.topic.name}",
    containerFactory = "departmentEventKafkaConsumerFactory"
  )
  public void consumeDepartmentEvent(DepartmentEvent deptEvent) {
    log.info("Consumed new department event from employee service");
    log.info(deptEvent.toString());

    Department dept = new Department();

    dept.setDeptId(deptEvent.getDept().getDeptId());
    dept.setDeptName(deptEvent.getDept().getDeptName());
    dept.setDeptOwner(deptEvent.getDept().getDeptOwner());

    Optional<Organization> orgOp = orgRepo.findById(deptEvent.getOrg().getOrgId());
    dept.setOrg(orgOp.get());
    
    if (!deptEvent.isDelete()) {
      deptRepo.save(dept);
      log.info("Saved department in db " + dept.toString());
    } else {
      if (deptRepo.existsById(dept.getDeptId())) {
        deptRepo.deleteById(dept.getDeptId());
        log.info("Deleted department in db " + dept.toString());
      }
    }
  }
}
