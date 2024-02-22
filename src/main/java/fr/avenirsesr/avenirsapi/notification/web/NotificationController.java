package fr.avenirsesr.avenirsapi.notification.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.service.NotificationService;

@RestController
@CrossOrigin(origins = "*")
public class NotificationController {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

	private int counter = 1;

	@Autowired
	private NotificationService service;
	
	@GetMapping("${avenirs.routes.notification.read}")
	public Iterable<Notification> getNotifications(){
		return service.getNotifications();
	}
	
	@GetMapping("${avenirs.routes.notification.create}")
	public String testNotifications(){
		final String message = "Test notification #" + counter++;
		final String audience =  Math.random() < 0.5 ? "*": Math.random() < 0.5 ? "deman" : "gribonvald";
		final Notification notification = new Notification(audience, message);
		
		LOGGER.debug("testNotifications sent: " + notification);
		service.send(notification);
		return message;
	}
	@CrossOrigin
	@PostMapping(value="${avenirs.routes.notification.create}")
	public String createNotification(String audience, String messageBody){
		LOGGER.debug("createNotification audience: " + audience);
		LOGGER.debug("createNotification messageBody: " + messageBody);
		
		final Notification notification = new Notification(audience, messageBody);
		
		LOGGER.debug("createNotification sent: " + notification);
		service.send(notification);
		return "OK";
	}

}
