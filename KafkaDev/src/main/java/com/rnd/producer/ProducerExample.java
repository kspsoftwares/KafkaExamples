package com.rnd.producer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

public class ProducerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	String topic = "quickstart-events" ;
		String key = "HouseNo" ;
		String value = "100" ;Change the values
		Properties config = new Properties();
		try {
			config.put("client.id", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//config.put("bootstrap.servers", "localhost:9092");
		config.put("acks", "all");
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		
		config.put("key.serializer", 
		         "org.apache.kafka.common.serialization.StringSerializer");
		         
		config.put("value.serializer", 
		         "org.apache.kafka.common.serialization.StringSerializer");
		      

		Producer<String, String> producer = new KafkaProducer<String,String>(config);
		final ProducerRecord record = new ProducerRecord(topic, key, value);
		producer.send(record, new Callback() {
		  public void onCompletion(RecordMetadata metadata, Exception e) {
		    if (e != null)
		      System.out.println("Send failed for record {}"+ record+ e);
		  }
		});
	*/
		   // Check arguments length value
	      //if(args.length == 0){
	        // System.out.println("Enter topic name");
	      //   return;
	     // }
	      
	      //Assign topicName to string variable
	      String topicName = "users-topic"; //"quickstart-events";//args[0].toString();
	      
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
	         "org.apache.kafka.common.serialization.StringSerializer");
	      
	      Producer<String, String> producer = new KafkaProducer
	         <String, String>(props);
	            
	      for(int i = 0; i < 5; i++)
	         producer.send(new ProducerRecord<String, String>(topicName, 
	            Integer.toString(i), "Message " + Integer.toString(i)));// +" at " + todaysDate() ));
	               System.out.println("Message sent successfully");
	               producer.close();
	   }
	
	
	private static String todaysDate() {
		String pattern = "MM/dd/yyyy HH:mm:ss";

		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date according to the chosen pattern
		DateFormat df = new SimpleDateFormat(pattern);

		// Get the today date using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String todayAsString = df.format(today);
		return todayAsString ;
	}
}
