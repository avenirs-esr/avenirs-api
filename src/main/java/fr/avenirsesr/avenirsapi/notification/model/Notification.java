/**
 * 
 */

package fr.avenirsesr.avenirsapi.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Representation of a Notification. This concerns the user notification as well as more internal ones
 * like System notification.
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notification")
public class Notification {
	
	private final static String BROADCAST="*";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/** Audience of the notification */
	private String[]  audience;
	
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
	 * Builds an instance of Notification for Broadcasting;
	 * @param header The header of the notification
	 * @param body The body (message) of the notification.
	 * @param footer Footer off the notification.
	 */
	public Notification(String header, String body, String footer) {
		this(BROADCAST , header, body, footer);
	}
	
	/**
	 * Builds an instance of Notification for Broadcasting;
	 * @param body The body (message) of the notification.
	 */
	public Notification(String body) {
		this(BROADCAST , null, body, null);
	}
	
	/**
	 * Builds an instance of Notification
	 * @param audience The audience of the notification.
	 * @param header The header of the notification
	 * @param body The body (message) of the notification.
	 * @param footer Footer off the notification.
	 */
	public Notification(@NonNull String[] audience, String header, String body, String footer) {
		this.audience = audience.clone();
		this.body = body;
		this.header = header;
		this.footer =  footer;
	}
	
	/**
	 * Builds an instance of Notification
	 * @param audience The audience of the notification.
	 * @param body The body (message) of the notification.
	 */
	public Notification(@NonNull String[] audience, String body) {
		this(audience, null, body, null);
	}
	
	/**
	 * Builds an instance of Notification
	 * @param audience The audience of the notification.
	 * @param header The header of the notification
	 * @param body The body (message) of the notification.
	 * @param footer Footer off the notification.
	 */
	public Notification(@NonNull String  audience, String header, String body, String footer) {
		this.audience = new String[] {audience};
		this.body = body;
		this.header = header;
		this.footer =  footer;
	}
	
	/**
	 * Builds an instance of Notification
	 * @param audience The audience of the notification.
	 * @param header The header of the notification
	 * @param body The body (message) of the notification.
	 * @param footer Footer off the notification.
	 */
	public Notification(@NonNull String  audience, String body) {
		this(audience, null, body, null);		
	}
	
	/**
	 * Determines if the notification has to be broadcasted.
	 * @return True if the notification targets everyone.
	 */
	boolean isBroadcast() {
		return getAudienceLength() == 1 ? BROADCAST.equals(audience[0]): false;
	}
	
	/**
	 * Gives the length of the audience.
	 * @return The number of entries in the audience array. 
	 */
	int getAudienceLength() {
		return audience == null ? 0 : audience.length;	
	}


}
