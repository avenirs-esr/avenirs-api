/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.model;

/**
 * Type of notification. 
 * This should impact the way the notification is handled.
 */
public enum NotificationType {
	SYSTEM, // For instance to change the log level.
	UI,     // For instance to trigger the refresh of a component.
	USER    // End user notification.

}
