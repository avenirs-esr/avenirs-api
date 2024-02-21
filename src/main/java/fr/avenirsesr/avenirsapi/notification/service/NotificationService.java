/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.avenirsesr.avenirsapi.AvenirsApiApplication;
import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.repository.NotificationRepository;
import fr.avenirsesr.avenirsapi.notification.web.NotificationWebsocketHandler;
import rx.Subscription;
import rx.subjects.ReplaySubject;

/**
 * Service associated to the notifications.
 */
@Service
public class NotificationService {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);


	/** Notification repository. */
	@Autowired
	private NotificationRepository repository;
	
	/** Kafka producer. */
	@Autowired
	private NotificationKafkaProducer producer;
	
	
	/** Kafka consumer. */
	@Autowired
	private NotificationKafkaConsumer consumer;
	
	
//	
//	/** Subscription to the Kafka consumer notification Observable. */
//	private Subscription subscription;


//	/**
//	 * Subscribe to the notifications Observable.
//	 */
//	 @Override
//	  public void afterPropertiesSet() {
//		 subscription = consumer.getNotifications$().subscribe((Notification notification) -> processNewNotification(notification));
//	  }
	 
//	 private void processNewNotification(Notification notification) {
//		 LOGGER.error("Processing new notification: " + notification);
//		 this.websocketHandler.process(notification);
//	 }
	 
	 /**
	  * Handles the unsubscription to notifications Observable.
	  */
//	 @Override
//	 public void destroy() {
//		 if (subscription != null) {
//			 subscription.unsubscribe();
//			 subscription = null;
//		 }
//	  }

	/**
	 * Gives all the notifications.
	 * 
	 * @return An iterable over all notifications.
	 */
	public Iterable<Notification> getNotifications() {
		return this.repository.findAll();
	}
	
	/**
	 * Send a notification.
	 * @param notification The notification to send.
	 */
	public void send(Notification notification) {
		LOGGER.debug("Notification: " + notification);
		// final  Notification notification = new Notification(Math.random() < 0.5 ? "deman" : "gribonvald", "My header", message, "My footer");
		final Notification res = repository.save(notification);
		LOGGER.debug("Initial notification " + notification);
		LOGGER.debug("Resulting notification " + res);
		this.producer.send(notification);
	}
	
	ReplaySubject<Notification> getNotifications$(){
		return this.consumer.getNotifications$();
	}
	
	
}
