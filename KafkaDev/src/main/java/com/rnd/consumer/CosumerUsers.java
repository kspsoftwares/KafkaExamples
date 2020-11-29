package com.rnd.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.rnd.users.User;

public class CosumerUsers {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  //Kafka consumer configuration settings
	      String topicName = "users-topic" ; //args[0].toString();
	      Properties props = new Properties();
	      
	      props.put("bootstrap.servers", "localhost:9092");
	      props.put("group.id", "test");
	      props.put("enable.auto.commit", "true");
	      props.put("auto.commit.interval.ms", "1000");
	      props.put("session.timeout.ms", "30000");
	      props.put("key.deserializer", 
	         "org.apache.kafka.common.serialization.StringDeserializer");
	      props.put("value.deserializer", 
	         "com.rnd.users.UserDeserializer");
	      KafkaConsumer<String, User> consumer = new KafkaConsumer<String, User>(props);
	      
	      //Kafka Consumer subscribes list of topics here.
	      consumer.subscribe(Arrays.asList(topicName)) ;
	      
	      //print the topic name
	      System.out.println("Subscribed to topic " + topicName);
	      int i = 0;
	      
	      while (true) {
	         ConsumerRecords<String, User> records = consumer.poll(100);
	         for (ConsumerRecord<String, User> record : records)
	         
	        //	 User user = (User)record.value() ;
	         // print the offset,key and value for the consumer records.
	         System.out.printf("offset = %d, key = %s, value = %s\n", 
	            record.offset(), record.key(), record.value().getName());
	      }
	   } 

}
