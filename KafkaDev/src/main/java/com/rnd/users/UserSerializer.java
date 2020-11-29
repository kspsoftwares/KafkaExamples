package com.rnd.users;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSerializer implements Serializer {

	@Override
	public byte[] serialize(String topic, Object data) {

		User user = (User) data ;
		byte[] returnval = null ;
		ObjectMapper objectMapper = new ObjectMapper() ;
		try {
			returnval = objectMapper.writeValueAsString(user).getBytes() ;
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		
		return returnval;
	}

}
