package ca.sheridancollege.khanmoam.security;

import ca.sheridancollege.khanmoam.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration class for security settings in the application.
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppSecurityConfig.class);

	private final UserDetailsServiceImpl userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;

	/**
	 * Constructs a new SecurityConfig with the specified UserDetailsServiceImpl.
	 *
	 * @param userDetailsService The user details service to be used for
	 *                           authentication.
	 */
	public AppSecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	LogAccessDeniedHandler logAccessDeniedHandler() {
		return new LogAccessDeniedHandler();
	}

	/**
	 * Configures the security filter chain and various security settings.
	 *
	 * @param http The HttpSecurity object for configuring security.
	 * @return The configured SecurityFilterChain.
	 * @throws Exception If an error occurs during configuration.
	 */
	/**
	 * Configures the security filter chain and various security settings.
	 *
	 * @param http The HttpSecurity object for configuring security.
	 * @return The configured SecurityFilterChain.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Disable HTTP Basic authentication
		http.httpBasic().disable()
				// Authorize requests based on URL patterns and roles
				.authorizeRequests(authorizeRequests -> authorizeRequests
						// Allow access to "/add/book" only for users with the "ADMIN" role
						.antMatchers("/add/book").hasRole("ADMIN")
						// Allow access to "/add/review" for authenticated users
						.antMatchers("/add/review").authenticated())
				// Redirect to "/permission-denied" in case of access denial
				.exceptionHandling().authenticationEntryPoint(logAccessDeniedHandler());

		// Handle exceptions related to access denial
		http.exceptionHandling(exceptionHandling -> {
			// Redirect to "/permission-denied" in case of access denial
			exceptionHandling.accessDeniedHandler((request, response, accessDeniedException) -> {
				response.sendRedirect("/permission-denied");
				logger.info("bugs was trying to access /admin/add-book");
			});
		})
				// Configure form-based login
				.formLogin(formLogin -> formLogin
						// Use "/login" as the login page
						.loginPage("/login")
						// Allow access to the login page for all users
						.permitAll())
				// Configure logout
				.logout(logout -> logout
						// Invalidate the session and clear authentication
						.invalidateHttpSession(true).clearAuthentication(true)
						// Use "/logout" as the logout URL
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						// Allow access to the logout URL for all users
						.permitAll())
				// Configure headers, specifically for allowing content to be embedded in frames
				// from the same origin
				.headers(headers -> headers.frameOptions().sameOrigin());

		// Build and return the configured SecurityFilterChain
		return http.build();
	}

	/**
	 * Configures the authentication provider with a custom user details service and
	 * password encoder.
	 *
	 * @return The configured DaoAuthenticationProvider.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}
}