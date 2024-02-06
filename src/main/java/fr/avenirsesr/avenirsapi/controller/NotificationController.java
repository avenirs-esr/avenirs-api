package fr.avenirsesr.avenirsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.avenirsesr.avenirsapi.model.Notification;
import fr.avenirsesr.avenirsapi.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService service;
	
	@GetMapping("notifications")
	public Iterable<Notification> getNotifications(){
		return service.getNotifications();
	}

}
