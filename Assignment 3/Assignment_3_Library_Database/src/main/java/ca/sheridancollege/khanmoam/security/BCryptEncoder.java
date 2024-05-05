package ca.sheridancollege.khanmoam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Configuration class providing a BCryptPasswordEncoder bean for password encoding.
 * This class is annotated with {@link Component} to indicate that it is a Spring component,
 * allowing it to be automatically discovered and registered during component scanning.
 */
@Component
@Configuration
public class BCryptEncoder {

    /**
     * Creates and returns a BCryptPasswordEncoder bean.
     *
     * @return BCryptPasswordEncoder bean for password encoding.
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}