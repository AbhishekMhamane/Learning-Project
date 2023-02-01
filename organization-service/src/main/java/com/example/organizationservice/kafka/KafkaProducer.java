package com.example.organizationservice.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.organizationservice.model.Organization;

@Service
public class KafkaProducer {

	@Value("${org.topic.name}")
	String orgTopicName;
	
	//private static final Logger LOGGER =LoggerFactory.getLogger(KafkaProducer.class);
      
	  
	  @Autowired 
	  KafkaTemplate<String,Organization> kafkaOrgTemp;
	  
	  public void sendOrganization(Organization org) {
		  
	  //Logger.info(String.format(, null))
	  System.out.println("Producer sending new organization details on kafka");
	  System.out.println(org); Message<Organization> message = MessageBuilder
	  .withPayload(org) .setHeader(KafkaHeaders.TOPIC, orgTopicName) .build();
	  
	  kafkaOrgTemp.send(message);
	  
	  }
	 
}
