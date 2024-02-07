package fr.avenirsesr.avenirsapi.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService service;
	
	@GetMapping("notifications")
	public Iterable<Notification> getNotifications(){
		return service.getNotifications();
	}

}
