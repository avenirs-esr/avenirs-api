/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;

/**
 * NotificationResponse
 * @author Arnaud Deman
 * 2024-02-20
 */
@Data
public class NotificationResponse {
	
	String user;
	Notification[] notifications;
	String time = new SimpleDateFormat("HH:mm").format(new Date());
	

	public NotificationResponse(@NonNull String user, @NonNull Notification[] notifications){
		this.user = user;
		this.notifications = notifications;
		
	}

}
