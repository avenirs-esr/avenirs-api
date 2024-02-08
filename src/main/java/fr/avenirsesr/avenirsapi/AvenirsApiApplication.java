package fr.avenirsesr.avenirsapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import fr.avenirsesr.avenirsapi.notification.model.Notification;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class) 
public class AvenirsApiApplication implements CommandLineRunner {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(AvenirsApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AvenirsApiApplication.class, args);
		final Notification n = new Notification();
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Starting Avenirs API");
		LOGGER.warn("Spring security is disabled");
		
	}

}
