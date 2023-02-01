package com.example.employeeservice.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.employeeservice.model.Organization;

@Service
public class KafkaConsumer {
	public static final String orgTopicName = "org_events";
	public static final String orgGroupId = "org_group";

//	@Value("${org.topic.name}")
//	String orgTopicName;
//	
//	@Value("${org.group-id}")
//	String orgTopicGroupId;
	
	@KafkaListener(topics = orgTopicName ,groupId = orgGroupId )
	public void consumeOrganization(Organization org)
	{
		
		System.out.println("Consumed new organization from employee service");
		System.out.println(org);
	}
}
