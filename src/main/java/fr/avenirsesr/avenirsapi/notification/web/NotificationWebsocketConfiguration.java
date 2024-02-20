/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import lombok.Getter;

/**
 * NotificationWebsocketConfiguration
 * @author Arnaud Deman
 * 2024-02-20
 */
@Configuration
@EnableWebSocket
public class NotificationWebsocketConfiguration implements WebSocketConfigurer {
	/** end point for the notification web socket. */
	@Getter
	@Value("${avenirs.routes.notification.ws}")
	private String route;
	
	
	/** CORS: Allowed Origin Pattern. */
	@Getter
	@Value("${avenirs.routes.notification.ws.cors}")
	private String cors;
	/** CORS: Allowed Origin Pattern. */
	@Getter
	@Value("${avenirs.notification.binary.message.limit.octets}")
	private int limit;
	
	

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new NotificationWebsocketHandler(), route).setAllowedOriginPatterns(cors);
	}

    @Bean
    ServletServerContainerFactoryBean createWebSocketContainer() {
	    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
	    container.setMaxBinaryMessageBufferSize(limit);
	    return container;
	}


}
