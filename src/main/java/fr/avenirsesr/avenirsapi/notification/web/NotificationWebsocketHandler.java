/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import lombok.Getter;

/**
 * NotificationWebsocketHandler
 * @author Arnaud Deman
 * 2024-02-20
 */
public class NotificationWebsocketHandler extends AbstractWebSocketHandler {

	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("Recieved text message" + message);
		session.sendMessage(new TextMessage("Text message recieved from server: " + message) );
	}

	/**
	 * 
	 */
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		session.sendMessage(new TextMessage("Binary message recieved from server payload length: " + message.getPayloadLength()) );
	}

}
