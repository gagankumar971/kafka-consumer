package com.consumer.kafkaexample;

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


@Configuration
@EnableKafka
public class ReceiverConfig {
@Value("${spring.kafka.bootstrap-servers}")
private String bootStrapServers;
//@Value("${consumer.group}")
//private String consumerGroup;
@Bean
public Map<String,Object> consumerConfig(){
Map<String,Object> consumerProperties=new HashMap<String,Object>();
consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG,consumerGroup);
return consumerProperties;
}
@Bean
public ConsumerFactory<String, Object> consumerFactory(){
return new DefaultKafkaConsumerFactory<>(consumerConfig(),new
StringDeserializer(),new JsonDeserializer<>(Object.class));
}
@Bean
public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
ConcurrentKafkaListenerContainerFactory<String, String> factory=new
ConcurrentKafkaListenerContainerFactory<>();
factory.setConsumerFactory(consumerFactory());
return factory;
}

}