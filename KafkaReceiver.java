
  package com.consumer.kafkaexample; import
  org.springframework.kafka.annotation.KafkaListener; import
  org.springframework.stereotype.Service;
  
  
  @Service public class KafkaReceiver {
  
  @KafkaListener(groupId = "java_in_use_topic", topics = "java_in_use_topic")
  public void consumeMessage(String message) {
  System.out.println("Got message: " + message); } }
 