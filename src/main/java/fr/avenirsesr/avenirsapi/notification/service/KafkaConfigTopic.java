/**
 * 
 */
package fr.avenirsesr.avenirsapi.notification.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * KafkaConfigTopic
 * @author Arnaud Deman
 * 2024-02-07
 */
@Configuration
public class KafkaConfigTopic {
	@Bean
    public NewTopic javaguidesTopic(){
        return TopicBuilder.name("javaguides")
                .build();
    }
}
