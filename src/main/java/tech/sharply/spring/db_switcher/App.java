package tech.sharply.spring.db_switcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceAutoConfiguration.class})
@EnableScheduling
public class App implements CommandLineRunner {

	private static ApplicationContext applicationContext;

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(App.class, args);
		checkBeansPresence("mongoUserRepository",
				"mongoEngineConfiguration",
				"sqlUserRepository",
				"sqlEngineConfiguration");
	}

	private static void checkBeansPresence(String... beans) {
		var logStr = new StringBuilder("Registered beans:").append(System.lineSeparator());
		for (String beanName : beans) {
			logStr.append(beanName).append(" -> ").append(applicationContext.containsBean(beanName) ? "✓" : "X").append(System.lineSeparator());
		}
		LOG.info(logStr.toString());
	}

	@Override
	public void run(String... args) {
		LOG.info("Running cli app");
	}
}
