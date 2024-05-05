package ca.sheridancollege.khanmoam.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {
	
	public NamedParameterJdbcTemplate configDatabase(DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}

}
