package com.rnd.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallBack implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Partition %s, Offset %s",metadata.partition(),metadata.offset())) ;
	}

}
