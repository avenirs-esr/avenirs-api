/**
 * 
 */
package fr.avenirsesr.avenirsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.avenirsesr.avenirsapi.model.Notification;
import fr.avenirsesr.avenirsapi.repository.NotificationRepository;

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
