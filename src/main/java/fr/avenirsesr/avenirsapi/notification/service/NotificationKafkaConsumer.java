/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import fr.avenirsesr.avenirsapi.notification.model.Notification;
import lombok.Getter;
import rx.subjects.ReplaySubject;

/**
 * NotificationKafkaConsumer
 * @author Arnaud Deman
 * 2024-02-08
 */
@Service
public class NotificationKafkaConsumer {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationKafkaConsumer.class);

	@Getter
	ReplaySubject<Notification> notifications$ = ReplaySubject.create(1);
		
	@KafkaListener(topics = "${avenirs.notification.kafka.topic}")
	public void listen(Notification notification) {
	    System.out.println("NotificationKafkaConsumer Received Message: " + notification);
	    notifications$.onNext(notification);
	}
	
	
}
