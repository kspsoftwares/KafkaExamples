package com.rnd.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.rnd.users.User;

public class ProducerUsers {
	public static void main(String[] args) {
		String topicName = "users-topic";//args[0].toString();

		// create instance for properties to access producer configs   
		Properties props = new Properties();

		//Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		//Set acknowledgements for producer requests.      
		props.put("acks", "all");

		//If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		//Specify buffer size in config
		props.put("batch.size", 16384);

		//Reduce the no of requests less than 0   
		props.put("linger.ms", 1);

		//The buffer.memory controls the total amount of memory available to the producer for buffering.   
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", 
				"org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", 
				"com.rnd.users.UserSerializer");
		Producer<String, User> producer = new KafkaProducer
				<String, User>(props);

		try {
			User user = new User("test","40") ;  
			for(int i = 0; i < 5; i++)	  {
				user.setName("User " +i);
				producer.send(new ProducerRecord<String, User>(topicName, 
						Integer.toString(i), user));
				System.out.println("User sent successfully");	               
			}
		}catch(Exception e) {
			e.printStackTrace() ;			
		}
		finally {
			producer.close();
		}
	}
}
