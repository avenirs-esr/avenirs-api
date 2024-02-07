package fr.avenirsesr.avenirsapi.health.service;

import org.springframework.stereotype.Service;

import fr.avenirsesr.avenirsapi.health.model.HealthCheck;

/**
 * Builds an instance of .
 * @author Arnaud Deman. 
 * 2024-25-07
 */
@Service
public class HealthService {
	
	/**
	 * Performs a health check.
	 * @return The result of the check.
	 */
	public HealthCheck check(){
		return new HealthCheck("Status OK");
	}

}
