package com.example.organizationservice.config;

import com.example.organizationservice.event_dtos.DepartmentEvent;
import com.example.organizationservice.event_dtos.OrganizationEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaConfig {

  @Value("${spring.kafka.boostrap-servers}")
  String boostrap_servers;

  @Value("${org.topic.name}")
  String orgTopicName;

  @Value("${dept.topic.name}")
  String deptTopicName;

  @Bean
  public NewTopic orgTopic() {
    return TopicBuilder.name(orgTopicName).build();
  }

  @Bean
  public NewTopic deptTopic() {
    return TopicBuilder.name(deptTopicName).build();
  }

  // producer config for organization

  @Bean
  public ProducerFactory<String, OrganizationEvent> orgProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrap_servers);
    configProps.put(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      StringSerializer.class
    );
    configProps.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      JsonSerializer.class
    );
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, OrganizationEvent> kafkaOrgTemplate() {
    return new KafkaTemplate<>(orgProducerFactory());
  }

  @Bean
  public ProducerFactory<String, DepartmentEvent> deptProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrap_servers);
    configProps.put(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      StringSerializer.class
    );
    configProps.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      JsonSerializer.class
    );
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, DepartmentEvent> kafkaDeptTemplate() {
    return new KafkaTemplate<>(deptProducerFactory());
  }
}
