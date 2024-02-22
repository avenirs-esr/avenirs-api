/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

/**
 * NotificationWebsocketConfiguration
 * @author Arnaud Deman
 * 2024-02-20
 */

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import fr.avenirsesr.avenirsapi.notification.service.NotificationService;
import lombok.Getter;

@Configuration
@EnableWebSocketMessageBroker
public class NotificationWebsocketConfiguration  implements WebSocketMessageBrokerConfigurer {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
	
	/** end point for the notification web socket. */
	@Getter
	@Value("${avenirs.routes.realtime}")
	private String route;
	
	/** CORS Settings. */
	@Getter
	@Value("${avenirs.routes.realtime.cors}")
	private String cors;
	
	/** CORS: Allowed Origin Pattern. */
	@Getter
	@Value("${avenirs.notification.binary.message.limit.octets}")
	private int limit;
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //config.enableSimpleBroker("/topic");
        config.enableSimpleBroker(route);
        //config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	LOGGER.debug("Registering stomp end points.");
    	LOGGER.debug("Registering stomp end points: cors");
         registry.addEndpoint("/rt-notification").setAllowedOriginPatterns(cors);
         registry.addEndpoint("/rt-notification").setAllowedOriginPatterns(cors).withSockJS();
    }
    
  




}
