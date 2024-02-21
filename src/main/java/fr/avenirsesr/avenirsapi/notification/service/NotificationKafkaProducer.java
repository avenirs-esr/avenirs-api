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

import fr.avenirsesr.avenirsapi.notification.model.Notification;
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
	private KafkaTemplate<Long, Notification> kafkaTemplate;
	
	
	/** Name of the topic used for the notifications. */
	@Getter
	@Value("${avenirs.notification.kafka.topic}")
	private String topic;

	
	/**
	 * Send a notification
	 * @param notification The notification to send
	 */
	public void send(Notification notification){
        LOGGER.info("Notification sent" + notification);
        LOGGER.info("Notification id" + notification.getId());
        kafkaTemplate.send(this.topic, notification.getId(), notification);
    }
	
}
