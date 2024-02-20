package fr.avenirsesr.avenirsapi.notification.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.service.NotificationService;

@RestController
public class NotificationController {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

	private int counter = 1;

	@Autowired
	private NotificationService service;
	
	@GetMapping("notifications")
	public Iterable<Notification> getNotifications(){
		return service.getNotifications();
	}
	
	@GetMapping("notifications/test")
	public String testNotifications(){
		final String message = "Test notification #" + counter++;
		LOGGER.debug("testNotifications message sent: " + message);
		service.notify(message);
		return message;
	}

}
