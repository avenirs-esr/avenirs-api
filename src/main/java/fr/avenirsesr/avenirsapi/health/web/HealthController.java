/**
 * 
 */
package fr.avenirsesr.avenirsapi.health.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.avenirsesr.avenirsapi.health.model.HealthCheck;
import fr.avenirsesr.avenirsapi.health.service.HealthService;

/**
 * HealthController
 * @author Arnaud Deman
 * 2024-02-07
 */
@RestController
public class HealthController {
	
	@Autowired
	private HealthService service;

	@GetMapping("health")
	public HealthCheck getNotifications(){
		return service.check();
	}
}
