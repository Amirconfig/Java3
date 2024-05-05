package ca.sheridancollege.khanmoam.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.khanmoam.beans.User;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;
import ca.sheridancollege.khanmoam.utils.Role;

/**
 * Controller class for handling user registration operations.
 */
@Controller
public class RegisterController {

	private final DatabaseAccess databaseAccess;
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	/**
	 * Constructs a new RegisterController with the specified DatabaseAccess.
	 * 
	 * @param databaseAccess The database access object.
	 */
	public RegisterController(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	/**
	 * Displays the registration form.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for displaying the registration form.
	 */
	@GetMapping("/register")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "register";
	}

	/**
	 * Handles the submission of the registration form. Registers the user and
	 * redirects to the login page.
	 * 
	 * @param userForm      The User object submitted through the form.
	 * @param bindingResult The result of the binding process.
	 * @return The redirect URL to the login page.
	 */
	@PostMapping("/register")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			logger.error(String.valueOf(bindingResult.getFieldError()));
			return "register";
		}
		userForm.setRole(Role.CLIENT);

		databaseAccess.addUser(userForm);
		return "redirect:/login";
	}
}