package ca.sheridancollege.khanmoam.services;

import ca.sheridancollege.khanmoam.beans.User;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;
import ca.sheridancollege.khanmoam.utils.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * Service class implementing the Spring Security UserDetailsService for user
 * authentication.
 */
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	private final DatabaseAccess databaseAccess;

	/**
	 * Constructs a new UserDetailsServiceImpl with the specified DatabaseAccess.
	 *
	 * @param databaseAccess The DatabaseAccess object for retrieving user
	 *                       information.
	 */
	public UserDetailsServiceImpl(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	/**
	 * Load user details by username for authentication.
	 *
	 * @param username The username of the user to load.
	 * @return UserDetails containing user information.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = getUserByUsername(username);

		if (user == null) {
			throw new IllegalArgumentException("User not found with username: " + username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRole()));
	}

	/**
	 * Maps user roles to Spring Security authorities.
	 *
	 * @param role The user's role.
	 * @return A collection of authorities.
	 */
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}

	/**
	 * Retrieves a user by username from the database.
	 *
	 * @param username The username of the user to retrieve.
	 * @return The User object representing the retrieved user.
	 */
	private User getUserByUsername(String username) {
		return databaseAccess.getUserByUsername(username);
	}
}