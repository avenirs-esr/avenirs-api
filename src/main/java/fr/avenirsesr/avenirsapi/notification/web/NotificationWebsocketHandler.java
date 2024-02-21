/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import fr.avenirsesr.avenirsapi.notification.model.Notification;
import fr.avenirsesr.avenirsapi.notification.model.NotificationQuery;
import fr.avenirsesr.avenirsapi.notification.model.NotificationResponse;
import fr.avenirsesr.avenirsapi.notification.service.NotificationKafkaConsumer;
import fr.avenirsesr.avenirsapi.notification.service.NotificationService;
import lombok.Getter;
import rx.Subscription;

/**
 * NotificationWebsocketHandler
 * @author Arnaud Deman
 * 2024-02-20
 */
//@PropertySource("classpath:application.properties")
// @Configuration
@Controller
public class NotificationWebsocketHandler  implements DisposableBean {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationWebsocketHandler.class);
	
	@Value("${avenirs.routes.notification.rt}")
	private String route;
	
	/** Kafka consumer. */
	@Autowired
	private NotificationKafkaConsumer consumer;
	
	 @Autowired
	 private SimpMessageSendingOperations messagingTemplate;

		/** Subscription to the Kafka consumer notification Observable. */
		private Subscription subscription;
	
	 
//	 private void processNewNotification(Notification notification) {
//		 LOGGER.error("Processing new notification: " + notification);
//		 this.websocketHandler.process(notification);
//	 }
	 
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
	 
	 private void subscribe(NotificationQuery query) {
		 dispose();
		 subscription = consumer.getNotifications$()
				 .map(notification -> {
					 System.out.println(" map ==> notification" + notification);
					 return notification;
				 })
				 .filter(notification -> notification.isForUser(query.getUser()))
				 
				 .subscribe((Notification notification) -> processNewNotification(notification));
	 }
	 private void processNewNotification(Notification notification) {
		 LOGGER.error("Processing new notification: " + notification);
		 System.err.println("--->" + notification);
			
			messagingTemplate.convertAndSend( "/notification/rt", new NotificationResponse("deman", new Notification[] {notification}));
	 }
	
	@SendTo("/notification/rt")
	@MessageMapping("/messages")
	public void register(NotificationQuery query) throws Exception {
		System.err.println(" !!!!!!!! Register" + query);
		LOGGER.debug("query" + query);
	    this.subscribe(query);
	    //return new NotificationResponse(query.getUser(),  new Notification[] {new Notification("foobar")}) ;
	}
	
	

}
