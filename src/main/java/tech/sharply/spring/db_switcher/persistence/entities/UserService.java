package tech.sharply.spring.db_switcher.persistence.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.sharply.spring.db_switcher.persistence.implementations.nosql.MongoUserRepository;
import tech.sharply.spring.db_switcher.persistence.implementations.sql.SqlUserRepository;

import javax.annotation.PostConstruct;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired(required = false)
	private MongoUserRepository mongoUserRepository;
	@Autowired(required = false)
	private SqlUserRepository sqlUserRepository;

	@PostConstruct
	private void init() {
		if (mongoUserRepository == null && sqlUserRepository == null) {
//			throw new RuntimeException("No user repository bean registered!");
			LOG.error("No user repository bean registered!");
		}
	}

}
