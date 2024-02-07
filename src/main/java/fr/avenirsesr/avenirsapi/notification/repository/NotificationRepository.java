/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.avenirsesr.avenirsapi.notification.model.Notification;

/**
 * Repository for the notifications.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
