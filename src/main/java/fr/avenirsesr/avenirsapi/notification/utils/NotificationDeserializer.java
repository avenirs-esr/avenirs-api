/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.utils;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.avenirsesr.avenirsapi.notification.model.Notification;

/**
 * NotificationDeserializer
 * @author Arnaud Deman
 * 2024-02-09
 */
public class NotificationDeserializer implements Deserializer<Notification> {
	/** Logger instance. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationDeserializer.class);
	
	/** Object mapper used to deserialize. */
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Notification deserialize(String topic, byte[] data) {
		try {
            if (data == null){
            	LOGGER.warn("Null received at deserializing");
                return null;
            }
            LOGGER.debug("Deserializing Notification from data: " + data);
            return objectMapper.readValue(new String(data, "UTF-8"), Notification.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Notification");
        }
	}

}
