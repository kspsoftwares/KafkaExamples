package com.rnd.users;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDeserializer implements Deserializer{

	@Override
	public User deserialize(String topic, byte[] data) {

		ObjectMapper mapper = new ObjectMapper() ;
		User user = null ;
		try {
			user = mapper.readValue(data,  User.class);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return user;
	}
}
