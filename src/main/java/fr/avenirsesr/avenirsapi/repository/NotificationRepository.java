/**
 * 
 */
package fr.avenirsesr.avenirsapi.repository;

import org.springframework.data.repository.CrudRepository;

import fr.avenirsesr.avenirsapi.model.Notification;

/**
 * Repository for the notifications.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
