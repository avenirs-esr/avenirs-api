/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.avenirsesr.avenirsapi.AvenirsApiApplication;
import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.repository.NotificationRepository;

/**
 * Service associated to the notifications.
 */
@Service
public class NotificationService {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);


	/** Notification repository. */
	@Autowired
	private NotificationRepository repository;
	
	/** Kafka producer. */
	@Autowired
	private NotificationKafkaProducer producer;


	/**
	 * Builds an instance of NotificationService.
	 */
	public NotificationService() {

	}

	/**
	 * Gives all the notifications.
	 * 
	 * @return An iterable over all notifications.
	 */
	public Iterable<Notification> getNotifications() {
		return this.repository.findAll();
	}
	
	/**
	 * Notify a message.
	 * @param message The message to send.
	 */
	public void notify(String message) {
		LOGGER.debug("Message: " + message);
		final  Notification notification = new Notification("My header", message, "My footer");
		final Notification res = repository.save(notification);
		LOGGER.debug("Initial notification " + notification);
		LOGGER.debug("Resulting notification " + res);
		this.producer.send(notification);
	}
}
