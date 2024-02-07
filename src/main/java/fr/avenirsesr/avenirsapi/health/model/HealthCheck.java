/**
 * 
 */
package fr.avenirsesr.avenirsapi.health.model;

import java.util.Date;

import lombok.Data;

/**
 * Health information.
 * Stub class.
 * @author A. Deman.
 */
@Data
public class HealthCheck {

	/** The date of the check. */
	private Date date;
	
	/** The message. */
	private String message;
	
	
	/**
	 * Builds an instance of HealthCheck.
	 * @param message The message associated to the check.
	 */
	public HealthCheck(String message) {
		this.date = new Date();
		this.message = message;
	}
}
