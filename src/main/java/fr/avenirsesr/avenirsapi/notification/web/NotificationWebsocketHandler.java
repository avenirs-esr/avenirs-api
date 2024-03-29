/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.model.NotificationQuery;
import fr.avenirsesr.avenirsapi.notification.model.NotificationResponse;
import fr.avenirsesr.avenirsapi.notification.service.NotificationKafkaConsumer;
import rx.Subscription;

/**
 * NotificationWebsocketHandler
 * 
 * @author Arnaud Deman 2024-02-20
 */
@Controller
public class NotificationWebsocketHandler implements DisposableBean {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationWebsocketHandler.class);

	@Value("${avenirs.routes.realtime}")
	private String route;

	/** Kafka consumer. */
	@Autowired
	private NotificationKafkaConsumer consumer;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	/** Subscription to the Kafka consumer notification Observable. */
	private Subscription subscription;
	
	/** Current Notification query. */
	private NotificationQuery query;

	/**
	 * Handles the unsubscription to notifications Observable.
	 */
	@Override
	public void destroy() {
		dispose();
	}

	private void dispose() {
		if (subscription != null) {
			subscription.unsubscribe();
			subscription = null;
		}
	}

	/**
	 * Subscribe to the notification observable.
	 * 
	 * @param query The query used to filter the notifications to process.
	 */
	private void subscribe(NotificationQuery query) {
		if (this.query != null &&  this.query.getUser().equals(query.getUser())) {
			dispose();
		}
		if (this.subscription == null) {
		subscription = consumer.getNotifications$().map(notification -> {
			LOGGER.debug(" map ==> notification" + notification);
			return notification;
		}).filter(notification -> notification.isForUser(query.getUser()))
				.subscribe((Notification notification) -> processNewNotification(query, notification));
		}
	}

	/**
	 * Process a new received notification.
	 * 
	 * @param notification The new notification.
	 */
	private void processNewNotification(NotificationQuery query, Notification notification) {
		LOGGER.trace("Processing new notification: " + notification);
		messagingTemplate.convertAndSend("/realtime",
				new NotificationResponse(query.getUser(), new Notification[] { notification }));
	}

	/**
	 * Processes a notification query.
	 * @param query The notification query to process.
	 * @param authHeader The authorization header.
	 */
	@SendTo("/realtime")
	@MessageMapping("/rt-notification")
	public void processNotificationQuery(@Payload NotificationQuery query,  @Header(value="Authorization", required=false) String authHeader) {
		LOGGER.debug("processNotificationQuery query: " + query);
		LOGGER.debug("processNotificationQuery authHeader: " + authHeader);
		this.subscribe(query);
	}
}
