package com.example.organizationservice.config;

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

import com.example.organizationservice.model.Organization;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value("${spring.kafka.boostrap-servers}")
	String boostrap_servers;
	
	@Value("${org.topic.name}")
	String orgTopicName;
	
	@Bean
	public NewTopic orgTopic()
	{
		return TopicBuilder.name(orgTopicName).build();
	}
	
	// producer config for organization
	
	@Bean
	public ProducerFactory<String, Organization> orgProducerFactory() {
	      Map<String, Object> configProps = new HashMap<>();
	      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrap_servers);
	      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	      return new DefaultKafkaProducerFactory<>(configProps);
	   }
	  
	 @Bean
	 public KafkaTemplate<String, Organization> kafkaTemplate() {
	      return new KafkaTemplate<>(orgProducerFactory());
	   }
	
	
}
