/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.Getter;

/**
 * 
 * NotificationKafkaProducer
 * @author Arnaud Deman
 * 2024-02-08
 */
@Service
public class NotificationKafkaProducer {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationKafkaProducer.class);
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	/** Name of the topic used for the notifications. */
	@Getter
	@Value("${avenirs.notification.kafka.topic}")
	private String topic;

	
	public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        kafkaTemplate.send(this.topic, message);
    }


}
