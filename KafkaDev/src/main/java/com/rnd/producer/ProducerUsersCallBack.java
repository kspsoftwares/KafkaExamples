package com.rnd.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.rnd.users.User;
import com.rnd.util.KafkaProperties;

public class ProducerUsersCallBack {
 
	public static void main(String[] args) {
		String topicName = "users-topic";
		ProducerUsersCallBack producer = new ProducerUsersCallBack() ;
		producer.sendUsersToKafka(topicName);
	}

	private void sendUsersToKafka(String topicName) {
		Properties props = KafkaProperties.getKafkaProperties();
		Producer<String, User> producer = new KafkaProducer
				<String, User>(props);
		ProducerCallBack producersCallBack = new ProducerCallBack() ;
		try {
			User user = new User("test","40") ;  
			for(int i = 0; i < 5; i++)	  {
				user.setName("User " +i);
				producer.send(new ProducerRecord<String, User>(topicName, 
						Integer.toString(i), user), producersCallBack);
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

