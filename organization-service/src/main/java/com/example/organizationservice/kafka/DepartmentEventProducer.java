package com.example.organizationservice.kafka;

import com.example.organizationservice.event_dtos.DepartmentEvent;
import com.example.organizationservice.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DepartmentEventProducer {

  @Value("${dept.topic.name}")
  String deptTopicName;

  @Autowired
  KafkaTemplate<String, DepartmentEvent> kafkaDeptTemp;

  private static final Logger log = LoggerFactory.getLogger(
    DepartmentEventProducer.class
  );

  public void sendDepartmentEvent(Department dept, boolean isDelete) {
    DepartmentEvent event = new DepartmentEvent(dept, isDelete);
    log.info("DepartmentEvent triggered : " + event);

    Message<DepartmentEvent> message = MessageBuilder
      .withPayload(event)
      .setHeader(KafkaHeaders.TOPIC, deptTopicName)
      .build();

    kafkaDeptTemp.send(message);
    
  }
}
