package tech.sharply.spring.db_switcher.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.sharply.spring.db_switcher.MongoEngineConfiguration;
import tech.sharply.spring.db_switcher.SqlEngineConfiguration;

import javax.annotation.PostConstruct;

@Component
public class DatabaseEngineIdentifier {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseEngineIdentifier.class);

	@Autowired(required = false)
	private SqlEngineConfiguration sqlEngineConfiguration;
	@Autowired(required = false)
	private MongoEngineConfiguration mongoEngineConfiguration;
	private DatabaseEngine engine;

	@PostConstruct
	private void init() {
		this.engine = sqlEngineConfiguration == null ? DatabaseEngine.NOSQL : DatabaseEngine.SQL;
		LOG.info("App started with DB engine: " + this.engine.name());
	}

	public enum DatabaseEngine {
		NOSQL,
		SQL;
	}

}
