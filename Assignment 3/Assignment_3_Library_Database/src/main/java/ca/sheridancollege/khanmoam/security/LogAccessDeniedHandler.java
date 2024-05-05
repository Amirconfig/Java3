package ca.sheridancollege.khanmoam.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Used to track unauthorized entry into a protected page and redirect to a page with a message about permission denied.
 */
public class LogAccessDeniedHandler implements AuthenticationEntryPoint {
	private static final Logger logger = LoggerFactory.getLogger(AppSecurityConfig.class);

	/**
	 * Invoked when an auth exception occurs.
	 *
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendRedirect("/permission-denied");
		logger.info("bugs was trying to access " + request.getRequestURI());

	}

}
