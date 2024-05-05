package ca.sheridancollege.khanmoam.beans;

import ca.sheridancollege.khanmoam.utils.Role;
import lombok.EqualsAndHashCode;

/**
 * Represents a user in the system. Each user has an ID, role, username, and
 * password.
 */
@EqualsAndHashCode
public class User {

	private Long id;
	private Role role;
	private String userName;
	private String password;

	/**
	 * Getter for ID
	 * 

	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for ID
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for Role
	 * 
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Setter for Role
	 * 
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Getter for username
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter for username
	 * 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter for password
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}