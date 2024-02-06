/**
 * 
 */

package fr.avenirsesr.avenirsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Representation of a Notification. This concerns the user notification as well as more internal ones
 * like System notification.
 */
@Data
@Entity
@Table(name="notification")
public class Notification {
	
	@Id
	@GeneratedValue(strategy  =GenerationType.IDENTITY)
	private Long id;
	
	/** The priority of the notification. */
	private NotificationPriority priority = NotificationPriority.MEDIUM;
	
	/** The type of the notification. */
	private NotificationType type = NotificationType.USER;
	
	
	/** Optional header of the notification. */
	private String header;
	
	/** Message */
	private String body;
	
	/** Optional footer of the notification. */
	private String footer;

	/**
	 * Builds an instance of Notification.
	 */
	public Notification() {
		// TODO Auto-generated constructor stub
	}

}
