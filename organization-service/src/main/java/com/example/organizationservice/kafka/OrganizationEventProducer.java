package com.example.organizationservice.kafka;

import com.example.organizationservice.event_dtos.OrganizationEvent;
import com.example.organizationservice.model.Organization;
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

@Service
public class OrganizationEventProducer {

  @Value("${org.topic.name}")
  String orgTopicName;

  private static final Logger log = LoggerFactory.getLogger(
    OrganizationEventProducer.class
  );

  @Autowired
  KafkaTemplate<String, OrganizationEvent> kafkaOrgTemp;

  public void sendOrganizationEvent(Organization org, boolean isDelete) {
    log.info("Organization event triggered");

    OrganizationEvent orgEvent = new OrganizationEvent();
    orgEvent.setOrganization(org);
    orgEvent.setDelete(isDelete);

    log.info(orgEvent.toString());

    Message<OrganizationEvent> message = MessageBuilder
      .withPayload(orgEvent)
      .setHeader(KafkaHeaders.TOPIC, orgTopicName)
      .build();

    kafkaOrgTemp.send(message);
  }
}
