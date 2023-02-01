package com.example.employeeservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.employeeservice.model.Organization;

@EnableKafka
@Configuration
public class ConsumersConfig {

	@Value("${spring.kafka.boostrap-servers}")
	String boostrap_servers;
	
	@Value("${org.topic.name}")
	String orgTopicName;
	
	@Value("${org.group-id}")
	String orgTopicGroupId;

	// consumer config for organization
    @Bean
    public ConsumerFactory<String, Organization> orgConsumerFactory() {
    	
    	 JsonDeserializer<Organization> deserializer = new JsonDeserializer<>(Organization.class);
    	    deserializer.setRemoveTypeHeaders(false);
    	    deserializer.addTrustedPackages("*");
    	    deserializer.setUseTypeMapperForKey(true);
    	    
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrap_servers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, orgTopicGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Organization> organizationkafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Organization>
                factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orgConsumerFactory());
        return factory;
    }
    
    
}

