package com.example.employeeservice.kafka;

import com.example.organizationservice.event_dtos.OrganizationEvent;
import com.example.organizationservice.event_dtos.DepartmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//import com.example.organizationservice.model.Organization;

@Service
public class Consumer {

  Logger log = LoggerFactory.getLogger(Consumer.class);

  public static final String orgTopicName = "org_events";
  public static final String deptTopicName = "dept_events";
  public static final String orgGroupId = "org_group";

  //	@Value("${org.topic.name}")
  //	String orgTopicName;
  //
  //	@Value("${org.group-id}")
  //	String orgTopicGroupId;

  @KafkaListener(topics = "org_events")
  public void consumeOrganizationEvent(OrganizationEvent orgEvent) {
  
	log.info("Consumed new organization event from employee service");
	log.info(orgEvent.toString());
  }

  // @KafkaListener(topics = deptTopicName,groupId = "dept_group")
  // public void consumeDepartmentEvent(String deptEvent) {

	// log.info("Consumed new department event from employee service");
	// log.info(deptEvent.toString());
  // }


}
