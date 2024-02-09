/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.utils;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.avenirsesr.avenirsapi.notification.model.Notification;

/**
 * NotificationSerializer
 * @author Arnaud Deman
 * 2024-02-09
 */
public class NotificationSerializer implements Serializer<Notification> {
	
	/** Logger instance. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSerializer.class);
	
	/** Object mapper used to serialize. */
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String topic, Notification notification) {
		try {
            if (notification == null){
                LOGGER.warn("Null received at serializing");
                return null;
            }
            LOGGER.debug("Serializing: "+notification);
            return objectMapper.writeValueAsBytes(notification);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
	}

}
