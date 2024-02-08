/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import fr.avenirsesr.avenirsapi.AvenirsApiApplication;
import lombok.Getter;

/**
 * KafkaConfigTopic
 * 
 * Initialization of the topic used for the notifications.
 * 
 * @author Arnaud Deman 2024-02-07
 */
@Configuration
public class NotificationKafkaConfigTopic {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(AvenirsApiApplication.class);

	/** Name of the topic used for the notifications. */
	@Getter
	@Value("${avenirs.notification.kafka.topic}")
	private String topic;

	/**
	 * Kafka topic initialization.
	 * 
	 * @return A new topic instance.
	 */
	@Bean
	NewTopic avenirsNotificationTopic() {
		LOGGER.info("Avenirs notification kafka topic: " + this.topic);
		return TopicBuilder.name(this.topic).build();
	}

}
