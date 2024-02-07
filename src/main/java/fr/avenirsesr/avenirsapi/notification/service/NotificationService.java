/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.repository.NotificationRepository;

/**
 * Service associated to the notifications.
 */
@Service
public class NotificationService {

	@Autowired
	private NotificationRepository repository;
	
	

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

}
