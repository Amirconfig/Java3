package ca.sheridancollege.khanmoam.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {
	
	@Bean
	public NamedParameterJdbcTemplate namedParemterJdbcTemplate(DataSource ds) {
	return new NamedParameterJdbcTemplate(ds);
	}
}