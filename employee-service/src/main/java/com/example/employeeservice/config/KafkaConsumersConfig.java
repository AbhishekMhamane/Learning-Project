package com.example.employeeservice.config;

import com.example.organizationservice.event_dtos.DepartmentEvent;
import com.example.organizationservice.event_dtos.OrganizationEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumersConfig {

  @Value("${spring.kafka.boostrap-servers}")
  String boostrap_servers;

  @Value("${org.group-id}")
  String orgTopicGroupId;

  @Value("${dept.group-id}")
  String deptTopicGroupId;

  // OrganizationEvent consumer configuaration

  @Bean
  public ConsumerFactory<String, OrganizationEvent> ConsumerFactory() {
    JsonDeserializer<OrganizationEvent> deserializer = new JsonDeserializer<>(
      OrganizationEvent.class
    );
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrap_servers);
    props.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      StringDeserializer.class
    );
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, orgTopicGroupId);

    return new DefaultKafkaConsumerFactory<>(
      props,
      new StringDeserializer(),
      deserializer
    );
  }

  @Bean(name = "organizationEventKafkaConsumerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, OrganizationEvent> ContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, OrganizationEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(ConsumerFactory());

    return factory;
  }

  // DepartmentEvent consumer configuration

  @Bean
  public ConsumerFactory<String, DepartmentEvent> departmentEventConsumerFactory() {
    JsonDeserializer<DepartmentEvent> deserializer = new JsonDeserializer<>(
      DepartmentEvent.class
    );
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrap_servers);
    props.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      StringDeserializer.class
    );
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, deptTopicGroupId);

    return new DefaultKafkaConsumerFactory<>(
      props,
      new StringDeserializer(),
      deserializer
    );
  }

  @Bean(name = "departmentEventKafkaConsumerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, DepartmentEvent> depatmentEventkafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, DepartmentEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(departmentEventConsumerFactory());

    return factory;
  }
}
